"Smalltalk Bank Program"
"Author: Trevor Smith"

Object subclass: Bank [

    |accounts|
    |s|
    Bank class [
        new [
            ^super new init
        ]
    ]

    init [
        accounts:= Set new
    ]
    
    add: account [
        accounts add: account.
    ]

    accrue: rate [
        accounts do: [:account | (account accrue: rate)]
	]
	
    asString [
        s:=''.
	    accounts do: [:account | s:=s,(account asString),(Character nl asString)].
        ^s
	]
]
"Main"
|bank| bank:=Bank new.
|customer| customer:=Customer new: 'Ann'.
|checking| checking:=CheckingAccount new:'01001' customer: customer balance: 100.00.
bank add: checking.
|savings| savings:=SavingAccount new: '01002' customer: customer balance: 200.00.
bank add: savings.
bank accrue: 0.02.
(bank asString) printNl.