package com.microservices.loans.controller.impl;

import com.microservices.loans.controller.ILoansController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoansControllerImpl implements ILoansController {
}
