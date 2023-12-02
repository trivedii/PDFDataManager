package com.valyx.pdfdatamanager.service;

import com.google.api.client.util.DateTime;
import com.valyx.pdfdatamanager.models.Transaction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PdfParserService {


    private DateTimeFormatter dateTimeFormatter;

    // Method to parse the pdf which returns all transactions in that pdf
    public List<Transaction> parseBankStatement( byte[] decodedBytes) throws IOException {

                PDDocument document = PDDocument.load(decodedBytes);
                PDFTextStripper pdfTextStripper = new PDFTextStripper();

                // Extract text from the PDF
                String pdfText = pdfTextStripper.getText(document);

        return processTextLineByLine(pdfText);
    }


    // processing line by line for transactions
    private List<Transaction> processTextLineByLine(String pageText) {
        // Split the page text into lines
        String[] parsedLines = pageText.split("\\r?\\n");
        List<String> lines = Arrays.asList(parsedLines);
//        List<String> modifiedLines = new ArrayList<>();
//        // Process each line
        List<Transaction> transactions = new ArrayList<>();
        StringBuilder str= new StringBuilder();
        for (String line : lines) {

            if(line.length()>=10) {
                if (line.charAt(2) == '/' && line.charAt(5) == '/') {
                    if(str.toString().charAt(2) == '/' && str.toString().charAt(5) == '/') {
                        transactions.add(createTransaction(str.toString()));
                    }
                        System.out.println(str);
                        str.delete(0, str.length());
                        str.append(line);
                }
                else{
                    str.append(line).append(" ");
                }
            }
        }
        return transactions;
    }


// creating a new transaction
    private Transaction createTransaction(String line){
        Transaction transaction = new Transaction();
        String[] parts = line.split("\\s+");

        //add Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate date = LocalDate.parse(parts[0],formatter);
        transaction.setDate(date);

        //add description
        transaction.setDescription(parts[1]);

        //remove ',' from amount
        parts[2] = makeAmountReadable(parts[2]);

        if(parts[2].charAt(0)=='+'){
            transaction.setCredit(parts[2]);
            transaction.setDebit("0");
        }
        else{
            transaction.setCredit("0");
            transaction.setDebit(parts[2]);
        }

        //remove ',' from amount
        parts[3] = makeAmountReadable(parts[3]);
        transaction.setBalance(parts[3]);

        return transaction;
    }


    //remove ',' from amount
    private String makeAmountReadable(String string){
        StringBuilder str = new StringBuilder();
        for(int i=0;i<string.length();i++){
            if(string.charAt(i)!=','){
                str.append(string.charAt(i));
            }
        }
        return str.toString();
    }

}
