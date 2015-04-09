element_pos(H,[H | T],1).
element_pos(Result,[H | T],N) :-
	N>1,
	N1 is N-1,
	element_pos(Result,T,N1).