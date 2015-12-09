def num_common_letters(goal_word, guess):
	goal=[]
	used=[]
	same=0
	for letter in goal_word:
		goal+=letter
	for letter in guess:
		if letter in goal and letter not in used:
			same+=1
			used+=letter
	return same
