package com.navayuvak.mandal.controller;

import java.math.BigDecimal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MandalController {

    private final JdbcTemplate db;

    public MandalController(JdbcTemplate db) {
        this.db = db;
    }

    // =========================
    // TEST
    // =========================

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "NAVAYUVAK TEST OK - NEW DEPLOYMENT";
    }

    // =========================
    // HOME
    // =========================

    @GetMapping("/")
    public String home(Model m) {

        Integer members = db.queryForObject(
                "SELECT COUNT(*) FROM members",
                Integer.class
        );

        BigDecimal collection = db.queryForObject(
                "SELECT COALESCE(SUM(amount),0) FROM collections",
                BigDecimal.class
        );

        BigDecimal expense = db.queryForObject(
                "SELECT COALESCE(SUM(amount),0) FROM expenses",
                BigDecimal.class
        );

        m.addAttribute("members", members);
        m.addAttribute("collection", collection);
        m.addAttribute("expense", expense);

        return "index";
    }

    // =========================
    // MEMBERS
    // =========================

    @GetMapping("/members")
    public String members(Model m) {

        m.addAttribute(
                "members",
                db.queryForList(
                        "SELECT * FROM members ORDER BY id DESC"
                )
        );

        return "members";
    }

    @PostMapping("/members/save")
    public String saveMember(
            @RequestParam String name,
            @RequestParam String mobile,
            @RequestParam String address,
            @RequestParam String joiningDate,
            @RequestParam String role) {

        db.update(
                "INSERT INTO members " +
                "(name,mobile,address,joining_date,role) " +
                "VALUES (?,?,?,?,?)",
                name,
                mobile,
                address,
                joiningDate,
                role
        );

        return "redirect:/members";
    }

    // =========================
    // COLLECTIONS
    // =========================

    @GetMapping("/collections")
    public String collections(Model m) {

        m.addAttribute(
                "collections",
                db.queryForList(
                        "SELECT c.*, m.name AS member_name " +
                        "FROM collections c " +
                        "LEFT JOIN members m ON c.member_id = m.id " +
                        "ORDER BY c.id DESC"
                )
        );

        m.addAttribute(
                "members",
                db.queryForList(
                        "SELECT id,name " +
                        "FROM members " +
                        "WHERE status='ACTIVE' " +
                        "ORDER BY name"
                )
        );

        return "collections";
    }

    @PostMapping("/collections/save")
    public String saveCollection(
            @RequestParam Integer memberId,
            @RequestParam String receiptNo,
            @RequestParam BigDecimal amount,
            @RequestParam String collectionDate,
            @RequestParam String paymentMode,
            @RequestParam String purpose,
            @RequestParam(required = false, defaultValue = "") String remarks) {

        db.update(
                "INSERT INTO collections " +
                "(member_id,receipt_no,amount,collection_date," +
                "payment_mode,purpose,remarks) " +
                "VALUES (?,?,?,?,?,?,?)",
                memberId,
                receiptNo,
                amount,
                collectionDate,
                paymentMode,
                purpose,
                remarks
        );

        return "redirect:/collections";
    }

    // =========================
    // EXPENSES
    // =========================

    @GetMapping("/expenses")
    public String expenses(Model m) {

        m.addAttribute(
                "expenses",
                db.queryForList(
                        "SELECT * FROM expenses ORDER BY id DESC"
                )
        );

        return "expenses";
    }

    @PostMapping("/expenses/save")
    public String saveExpense(
            @RequestParam String expenseDate,
            @RequestParam String category,
            @RequestParam String description,
            @RequestParam BigDecimal amount,
            @RequestParam String paidTo,
            @RequestParam String paymentMode,
            @RequestParam(required = false, defaultValue = "") String remarks) {

        db.update(
                "INSERT INTO expenses " +
                "(expense_date,category,description,amount," +
                "paid_to,payment_mode,remarks) " +
                "VALUES (?,?,?,?,?,?,?)",
                expenseDate,
                category,
                description,
                amount,
                paidTo,
                paymentMode,
                remarks
        );

        return "redirect:/expenses";
    }

    // =========================
    // REPORT
    // =========================

    @GetMapping("/report")
    public String report(Model m) {

        BigDecimal collection = db.queryForObject(
                "SELECT COALESCE(SUM(amount),0) FROM collections",
                BigDecimal.class
        );

        BigDecimal expense = db.queryForObject(
                "SELECT COALESCE(SUM(amount),0) FROM expenses",
                BigDecimal.class
        );

        BigDecimal balance = collection.subtract(expense);

        m.addAttribute("collection", collection);
        m.addAttribute("expense", expense);
        m.addAttribute("balance", balance);

        return "report";
    }
}
git add src/main/java/com/navayuvak/mandal/controller/MandalController.java

x

^X

G



