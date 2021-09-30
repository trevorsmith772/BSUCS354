"Smalltalk Checking Account class"
"Author: Trevor Smith"

Account subclass: CheckingAccount [

    CheckingAccount class [
        new: num customer: cust balance: bal [
            ^super new init: num customer: cust balance: bal.
        ]
    ]

    init: num customer: cust balance: bal[
        number:=num.
        customer:=cust.
        balance:=bal.
    ]

    accrue: rate[]

]
"Testing"
" |account| account:= CheckingAccount new: '1001' customer: 'Trevor' balance:'30'.
(account asString) printNl.
|customer2| customer2:= Customer new: 'Trev'.
(customer2 asString) printNl.
|account2| account2:= CheckingAccount new: '1002' customer: 'Trev' balance:'60'.
(account2 asString) printNl. "
