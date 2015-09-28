def num_common_letters(goal_word, guess):
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
		
