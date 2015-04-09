add_list(H,T,[H|T]).
reverse([],Result,Result).
reverse([H|T],Temp,Result) :-
	reverse(T,[H|Temp],Result).
add(N,A,[],[A|N]).
add(N,A,[A|T],Result) :-
	function([A|T],N,Result).
add(N,A,[B|T],Result) :-
	add_list(B,T,T1),
	add_list(A,N,Temp),
	function(T1,Temp,Result).
eliminate(N,Result) :-
	function(N,[],Result1),
	reverse(Result1,[],Result).
function([A|T],Temp,Result) :-
	add(Temp,A,T,Result),!.