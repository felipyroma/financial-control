package com.example.financialcontrol;

import com.example.financialcontrol.service.impl.ExcelService;
import com.example.financialcontrol.service.base.IBankStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class FinancialControlApplication implements CommandLineRunner {

    private final Map<String, IBankStatementService> services;

    @Autowired
    private ExcelService excelService;

    public FinancialControlApplication(Map<String, IBankStatementService> services) {
        this.services = services;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinancialControlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
//        System.out.println("Selecione o caminho do arquivo: ");
//        String filePath = scan.nextLine();

//        System.out.println("Selecione o banco: ");
//        String filePath = scan.nextLine();

        System.out.println(
                """
				Digite:
				1. importar arquivo excel
				2. exportar para base de dados""");
        int numberChosen = scan.nextInt();
        String statement = "itau";
        String filePath = "C:\\Users\\fe-ro\\Downloads\\itau_extrato_072025.pdf";
        String userHome = System.getProperty("user.home");
        String fileName = "saida.xlsx";
        String excelPath = userHome + File.separator + "Downloads" + File.separator + fileName;

        try {
            switch (numberChosen) {
                case 1:
                    excelService.importExcelFile(filePath, excelPath, statement, services);
                    break;
                case 2:
                    excelService.saveData();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erro no processamento " + e.getMessage());
        }
    }
}
