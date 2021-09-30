package main

import "fmt"

type CheckingAccount struct {
	number   string
	customer Customer
	balance  float32
}

func (a *CheckingAccount) accrue(rate float32) {}

func (a *CheckingAccount) deposit(amount float32) {
	a.balance = a.balance + amount
}

func (a *CheckingAccount) withdraw(amount float32) {
	a.balance = a.balance - amount
}
func (a *CheckingAccount) toString() string {
	return fmt.Sprintf("%s : %s : %f", a.number, a.customer.toString(), a.balance)
}
