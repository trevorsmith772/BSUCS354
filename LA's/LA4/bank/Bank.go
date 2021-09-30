package main

import "fmt"

type Bank struct {
	checkings [10]CheckingAccount
	savings   [10]SavingsAccount
	i1        int
	i2        int
}

func (b *Bank) addChecking(account CheckingAccount) {
	b.checkings[b.i1] = account
	b.i1++
}

func (b *Bank) addSavings(account SavingsAccount) {
	b.savings[b.i2] = account
	b.i2++
}

func (b *Bank) accrue(rate float32) {
	for i := 0; i < len(b.savings); i++ {
		if b.savings[i].customer.toString() != "" {
			b.savings[i].accrue(rate)
		}
	}
}

func (b *Bank) toString() string {
	ret := ""
	for _, account := range b.checkings {
		if account.customer.toString() != "" {
			ret += fmt.Sprintf("%s \n", account.toString())
		}

	}
	for _, account := range b.savings {
		if account.customer.toString() != "" {
			ret += fmt.Sprintf("%s \n", account.toString())
		}
	}
	return ret
}

func main() {
	bank := Bank{}
	customer := Customer{"Jason"}
	check := CheckingAccount{"01001", customer, 100.00}
	save := SavingsAccount{"01002", customer, 200.00, 0}
	bank.addChecking(check)
	bank.addSavings(save)
	bank.accrue(0.02)
	fmt.Println(bank.toString())
}
