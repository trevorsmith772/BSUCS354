"Smalltalk Customer class"

"Author: Trevor Smith"

Object subclass: Customer [
    |name|

    Customer class [
        new: customerName [
            ^super new init: customerName.
        ]
    ]

    init: custName [
        name:=custName.
    ]

    asString [
        ^name.
    ]
]

"Testing"
" |customer| customer:= Customer new:'Trevor'.
printNl.
(customer asString) printNl. "