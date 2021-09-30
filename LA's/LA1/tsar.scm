#!/bin/guile!#
;;Scheme Tree Search And Copy program
;; Trevor Smith
(define (tsar subj srch repl)
    (if (not (list? subj))
        (if (eq? subj srch) ;not a list
            repl
            subj
        )
        (if (null? subj) ;is a list
            (if (eq? subj srch) ;empty list
                repl
                subj
            )
            (if (myeq? (car subj) srch)   ;not an empty list
                (if (list? repl)
                    (cons (car (replace (car subj) repl)) (tsar (cdr subj) srch repl)) ;recurse with repl
                    (cons (replace (car subj) repl) (tsar (cdr subj) srch repl)) ;recurse with repl
                )
                (cons (car subj) (tsar (cdr subj) srch repl))    ;recurse with car subj
            ) 
        )
    )
)
; (define (randm repl)
;     (define newRepl '())
;     (define rand '(RANDOM))
;     (if (eq? (cdr repl) (car rand))
;         (cons (random 100) newRepl)
;         (cons (cdr repl) newRepl)
;     )
;     (if (eq? (car repl) (car rand))
;         (cons (random 100) newRepl)
;         (cons (car repl) newRepl)
;     )
;     newRepl
; )
(define (myeq? sub sch)
    (cond   
            ((and (list? sub) (not (list? sch))) ;(x) x
                (myeq? (car sub) sch))
            ((and (list? sub) (list? sch))  ;(x) (x)
                (if (null? sch)
                    (eq? (cdr sub) sch)
                    (myeq? (car sub) (car sch))
                ))
            ((and (not(list? sub)) (not (list? sch))) ;x x
                (eq? sub sch))
            (else
                #f)
    )
)
(define (replace carsub repl)

	(if (list? carsub)
		(replace (car carsub) (list repl))
        repl
	)
)

; Test Suite
(define (test)                                                        ;Expected output
    (display (tsar 'z 'x 'y))(newline)                                ;z
    (display (tsar 'x 'x 'y))(newline)                                ;y
    (display (tsar 'x 'x 'x))(newline)                                ;x
    (display (tsar '() 'x 'y))(newline)                               ;()
    (display (tsar '(x x) 'x 'y))(newline)                            ;(y y)
    (display (tsar '() '() 'x))(newline)                              ;x
    (display (tsar '(x (x) z) 'x 'y))(newline)                        ;(y (y) z)
    (display (tsar '(x (x) z) '(x) '(y y)))(newline)                  ;(x (y y) z)
    (display (tsar '(x (x) ((x)) z) '(x) '(y y)))(newline)            ;(x (y y) ((y y)) z)
    (display (tsar '(x (x) ((x)) z) '(x) '()))(newline)               ;(x () (()) z)
    (display (tsar '(x (x) ((x)) z) '() '(y y)))(newline)             ;(x (x y y) ((x y y) y y) z y y)
    (display (tsar '(x (x) ((x)) z) '(x) '(RANDOM y)))(newline)       ;(x (74 y) ((46 y)) z)
    (display (tsar '(x (x) ((x)) z) '(x) '(RANDOM RANDOM)))(newline)  ;(x (76 76) ((63 63)) z)
)