package com.navayuvak.mandal.controller;

import java.math.BigDecimal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MandalController {
 private final JdbcTemplate db;
 public MandalController(JdbcTemplate db){this.db=db;}

 @GetMapping("/")
 public String home(Model m){
  m.addAttribute("members",db.queryForObject("SELECT COUNT(*) FROM members",Integer.class));
  m.addAttribute("collection",db.queryForObject("SELECT COALESCE(SUM(amount),0) FROM collections",BigDecimal.class));
  m.addAttribute("expense",db.queryForObject("SELECT COALESCE(SUM(amount),0) FROM expenses",BigDecimal.class));
  return "index";
 }

 @GetMapping("/members")
 public String members(Model m){
  m.addAttribute("members",db.queryForList("SELECT * FROM members ORDER BY id DESC"));
  return "members";
 }

 @PostMapping("/members/save")
 public String saveMember(@RequestParam String name,@RequestParam String mobile,
  @RequestParam String address,@RequestParam String joiningDate,@RequestParam String role){
  db.update("INSERT INTO members(name,mobile,address,joining_date,role) VALUES(?,?,?,?,?)",
   name,mobile,address,joiningDate,role);
  return "redirect:/members";
 }

 @GetMapping("/collections")
 public String collections(Model m){
  m.addAttribute("collections",db.queryForList(
   "SELECT c.*,m.name member_name FROM collections c LEFT JOIN members m ON c.member_id=m.id ORDER BY c.id DESC"));
  m.addAttribute("members",db.queryForList("SELECT id,name FROM members WHERE status='ACTIVE' ORDER BY name"));
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
         "INSERT INTO collections(member_id,receipt_no,amount,collection_date,payment_mode,purpose,remarks) VALUES(?,?,?,?,?,?,?)",
         memberId, receiptNo, amount, collectionDate,
         paymentMode, purpose, remarks
     );

     return "redirect:/collections";
 }

 @GetMapping("/expenses")
 public String expenses(Model m){
  m.addAttribute("expenses",db.queryForList("SELECT * FROM expenses ORDER BY id DESC"));
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
         "INSERT INTO expenses(expense_date,category,description,amount,paid_to,payment_mode,remarks) VALUES(?,?,?,?,?,?,?)",
         expenseDate, category, description, amount,
         paidTo, paymentMode, remarks
     );

     return "redirect:/expenses";
 }

 @GetMapping("/report")
 public String report(Model m){
  BigDecimal c=db.queryForObject("SELECT COALESCE(SUM(amount),0) FROM collections",BigDecimal.class);
  BigDecimal e=db.queryForObject("SELECT COALESCE(SUM(amount),0) FROM expenses",BigDecimal.class);
  m.addAttribute("collection",c); m.addAttribute("expense",e); m.addAttribute("balance",c.subtract(e));
  return "report";
 }
}