factorial_1(0,1).
factorial_1(N,Result) :-
	N>0,
	N1 is N-1,
	factorial_1(N1,Result1),
	Result is N * Result1,!.
factorial_2(N, Result) :-
	function(N,1,Result).
function(0,Result,Result).
function(N,T,Result) :-
	N>0,
	N1 is N-1,
	T1 is T * N,
	function(N1,T1,Result),!.