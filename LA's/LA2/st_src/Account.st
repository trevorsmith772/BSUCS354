"Smalltalk Account class"

"Author: Trevor Smith"
Object subclass: Account [
    
    "Instance Variables"
    |number| 
    |customer|
    |balance|
    
    "Constructor"
    Account class [
        new [
            ^super new
        ]
    ]

    accrue: rate[
        ^self subclassResponsibility
    ]

    balance [
        ^balance.
    ]

    deposit: amount [
        balance:= balance + amount.
    ]

    withdrawl: amount [
        balance:= balance - amount.
    ]

    asString [
        ^(number, ':', (customer asString), ':', (balance asString)).
    ]
]