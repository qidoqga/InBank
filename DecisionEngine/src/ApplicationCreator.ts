import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-loan-application',
    template: `
    <form (submit)="onSubmit()">
      <label for="personalCode">Personal Code:</label>
      <input type="text" id="personalCode" name="personalCode" [(ngModel)]="loanApplication.personalCode" required>

      <label for="loanAmount">Loan Amount:</label>
      <input type="number" id="loanAmount" name="loanAmount" [(ngModel)]="loanApplication.loanAmount" required>

      <label for="loanPeriod">Loan Period:</label>
      <input type="number" id="loanPeriod" name="loanPeriod" [(ngModel)]="loanApplication.loanPeriod" required>

      <button type="submit">Submit</button>
    </form>

    <p *ngIf="decisionEngine">
      Decision: {{decisionEngine.approved ? 'Approved' : 'Rejected'}}, Max Approved Amount: {{decisionEngine.maxApprovedAmount}}
    </p>
  `,
})
export class LoanApplicationComponent {
    loanApplication = {
        personalCode: '',
        loanAmount: 0,
        loanPeriod: 0,
    };

    decisionEngine: DecisionEngine;

    constructor(private http: HttpClient) {}

    onSubmit() {
        this.http
            .post<DecisionEngine>('/api/loan', this.loanApplication)
            .subscribe((result) => (this.decisionEngine = result));
    }
}
