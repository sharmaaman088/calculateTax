package com.itbulls.PrinzWeb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaxCalculatorController {

    @PostMapping("/calculate")
    public String calculate(@RequestParam("prices") double[] prices, Model model) {
        List<Double> taxList = new ArrayList<>();
        List<Double> totalList = new ArrayList<>();
        double grandTotal = 0.0;         // Use double for grand total
        double totalTaxesPaid = 0.0;     // Use double for total taxes paid

        for (double price : prices) {
            double tax = price * 0.13;     // Calculate tax (13%)
            double total = tax + price;    // Total price including tax
            taxList.add(tax);              // Add tax to the list
            totalList.add(total);          // Add total price to the list
            grandTotal += total;           // Accumulate grand total
            totalTaxesPaid += tax;         // Calculate total taxes paid
        }

        // Add attributes to the model to pass to the view
        model.addAttribute("prices", prices);
        model.addAttribute("taxes", taxList);
        model.addAttribute("totals", totalList);
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("totalTaxesPaid", totalTaxesPaid); // Add total taxes paid to the model

        return "result"; // Return the name of the result view
    }
}
