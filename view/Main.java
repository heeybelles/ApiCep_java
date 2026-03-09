//Eduarda Belles
package br.edu.fatecpg.apicep.view;

import br.edu.fatecpg.apicep.model.Endereco;
import br.edu.fatecpg.apicep.service.ConsomeApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<String> historicoCeps = new ArrayList<>();
        List<Endereco> enderecos = new ArrayList<>();

        Gson gson = new Gson();
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 4) {

            System.out.println("\n--- SISTEMA VIA CEP ---");
            System.out.println("1. Buscar Endereço");
            System.out.println("2. Excluir Endereço");
            System.out.println("3. Histórico de Buscas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    try {

                        boolean cepValido = false;

                        while (!cepValido) {

                            System.out.println("Digite o CEP:");
                            String cepDigitado = scanner.nextLine();

                            // Remove hífen
                            cepDigitado = cepDigitado.replace("-", "");

                            // Verifica se tem exatamente 8 números
                            if (!cepDigitado.matches("\\d{8}")) {
                                System.out.println("CEP inválido. Digite exatamente 8 números.");
                                continue; // volta para digitar novamente
                            }

                            historicoCeps.add(cepDigitado);

                            boolean cepExiste = false;

                            for (Endereco endereco : enderecos) {

                                if (endereco.getCep().replace("-", "")
                                        .equals(cepDigitado)) {

                                    cepExiste = true;
                                    System.out.println("Endereço já cadastrado: " + endereco);
                                    cepValido = true;
                                    break;
                                }
                            }

                            if (!cepExiste) {

                                String respostaApi = ConsomeApi.buscaCep(cepDigitado);

                                try {

                                    Endereco novoEndereco = gson.fromJson(respostaApi, Endereco.class);

                                    if (novoEndereco.getCep() != null) {

                                        enderecos.add(novoEndereco);
                                        System.out.println("Novo endereço adicionado: " + novoEndereco);
                                        cepValido = true;

                                    } else {

                                        System.out.println("CEP inválido ou não encontrado. Digite novamente.");
                                    }

                                } catch (Exception erroJson) {

                                    System.out.println("CEP inválido. Digite novamente.");
                                }
                            }
                        }

                    } catch (IOException | InterruptedException erro) {

                        System.out.println("Erro ao buscar o CEP: " + erro.getMessage());
                    }

                    break;

                case 2:

                    System.out.println("Digite o CEP que deseja excluir:");
                    String cepExcluir = scanner.nextLine().replace("-", "");

                    boolean removido = enderecos.removeIf(endereco ->
                            endereco.getCep().replace("-", "")
                                    .equals(cepExcluir));

                    if (removido) {

                        System.out.println("Endereço removido com sucesso!");

                    } else {

                        System.out.println("CEP não encontrado na lista.");
                    }

                    break;

                case 3:

                    System.out.println("--- Histórico de CEPs Digitados ---");

                    if (historicoCeps.isEmpty()) {

                        System.out.println("Nenhum histórico disponível.");

                    } else {

                        historicoCeps.forEach(System.out::println);
                    }

                    System.out.println("\n--- Endereços na Lista ---");

                    if (enderecos.isEmpty()) {

                        System.out.println("Nenhum endereço cadastrado.");

                    } else {

                        enderecos.forEach(System.out::println);
                    }

                    break;

                case 4:

                    System.out.println("Saindo...");
                    break;

                default:

                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}