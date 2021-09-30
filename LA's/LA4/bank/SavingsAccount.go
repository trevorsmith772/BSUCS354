package main

import "fmt"

type SavingsAccount struct {
	number   string
	customer Customer
	balance  float32
	interest float32
}

func (a *SavingsAccount) accrue(rate float32) {
	add := a.balance * rate
	a.interest += add
	a.balance += add
}

func (a *SavingsAccount) deposit(amount float32) {
	a.balance = a.balance + amount
}

func (a *SavingsAccount) withdraw(amount float32) {
	a.balance = a.balance - amount
}
func (a *SavingsAccount) toString() string {
	return fmt.Sprintf("%s : %s : %f", a.number, a.customer.toString(), a.balance)
}
