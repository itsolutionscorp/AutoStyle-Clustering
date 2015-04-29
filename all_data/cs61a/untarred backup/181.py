def num_common_letters(goal_word, guess):
	"""Returns the number of letters in goal_word that are also in guess.
		As per the rules of the game, goal_word cannot have any repeated
		letters, but guess is allowed to have repeated letters.
		goal_word and guess are assumed to be of the same length.
		goal_word and guess are both instances of the word ADT.

	>>> mwfs, mwfl = make_word_from_string, make_word_from_list
	>>> num_common_letters(mwfs('steal'), mwfs('least'))
	5
	>>> num_common_letters(mwfs('steal'), mwfl(['s', 't', 'e', 'e', 'l']))
	4
	>>> num_common_letters(mwfl(['s', 't', 'e', 'a', 'l']), mwfs('thief'))
	2
	>>> num_common_letters(mwfl(['c', 'a', 'r']), mwfl(['p', 'e', 't']))
	0
	"""
	"*** YOUR CODE HERE ***"
	total=0
	
	def short(word):
		num=len(word)
		xx=word[0]
		k=1
		while k<=num-1:
			if word[k] !=word[k-1] :
				xx=xx+word[k]
			k=k+1
		return xx
	
	guess=short(guess)
	goal_word=short(goal_word)
	num_g=len(guess)
	num=len(goal_word)
	
	k=0
	
	while k<=num-1:
		i=0
		while i<=num_g-1:
			if goal_word[k] == guess[i]:
				total=total+1
			i=i+1
		k=k+1
	return total		
		


