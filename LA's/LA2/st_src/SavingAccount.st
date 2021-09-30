"Smalltalk Savings Account Class"
"Author: Trevor Smith"

Account subclass: SavingAccount [
    |interest|
    
    SavingAccount class [
        new: num customer: cust balance: bal [
            ^super new init: num customer: cust balance: bal.
        ]
    ]

    init: num customer: cust balance: bal [
        number:=num.
        customer:=cust.
        balance:=bal.
        interest:=0
    ]

    accrue: rate [
        interest:=interest + (balance*rate).
        balance:=balance + (balance*rate).
    ]

]
"Testing"
" |acount| account:= SavingAccount new:'0001' customer:'Trevor' balance: 300.
(account asString) printNl.
account accrue: 0.35.
(account asString) printNl.
|cust| cust:=Customer new:'Trev'.
(cust asString) printNl.
|account2| account2:=SavingAccount new:'0002' customer: cust balance: 400.
(account2 asString) printNl.
account2 accrue: 0.2.
(account2 asString) printNl "