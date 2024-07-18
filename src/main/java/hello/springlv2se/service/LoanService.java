package hello.springlv2se.service;

import hello.springlv2se.repository.LoanRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

}
