#!/bin/gprolog --consult-file

:- include('data.pl').
:- include('uniq.pl').

% Time slot meeting program
% Author: Trevor Smith

% Finds start and end time
lte(time(_,_,am),time(_,_,pm)).

% Checks differences in hours between each person
lte(time(FirstHour,_,Id),time(SecondHour,_,Id)) :-
		FirstHour<SecondHour.

% Check minutes for each person to see if different.
lte(time(Hour,FirstMin,Id),time(Hour,SecondMin,Id)) :-
		FirstMin =< SecondMin.

match(slot(FirstStart,FirstEnd),slot(SecondStart,SecondEnd),slot(SecondStart,SecondEnd)) :-
    lte(FirstStart,SecondStart),
    lte(SecondStart,FirstEnd),
    lte(SecondEnd,FirstEnd),
    SecondStart\==SecondEnd.

match(slot(FirstStart,FirstEnd),slot(SecondStart,SecondEnd),slot(SecondStart,FirstEnd)) :-
    lte(FirstStart,SecondStart),
    lte(SecondStart,FirstEnd),
    lte(FirstEnd,SecondEnd),
    SecondStart\==FirstEnd.

meetCheck(FirstSlot,SecondSlot,MeetingSlot) :-
    match(FirstSlot,SecondSlot,MeetingSlot).

meetCheck(FirstSlot,SecondSlot,MeetingSlot) :-
    match(SecondSlot,FirstSlot,MeetingSlot).

meetCollect([Second|Tail],FirstSlot,Slot) :-
    free(Second,SecondSlot),
    meetCheck(FirstSlot,SecondSlot,Slot0),
    meetCollect(Tail,Slot0,Slot).
    
meetCollect([],Slot,Slot).

meetTimes([First|Tail],Slot) :-
    free(First,FirstSlot),
    meetCollect(Tail,FirstSlot,Slot).

meet(Slot) :-
    people(People),
    meetTimes(People,Slot).

% Change for meeting times of different people
people([ann,bob,carla]).

main :- findall(Slot, meet(Slot), Slots),
        uniq(Slots, Uniq),
        write(Uniq), nl, halt.

:- initialization(main).
