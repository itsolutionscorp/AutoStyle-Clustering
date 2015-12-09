def num_common_letters(goal_word, guess):
	dic = set('')
	counter = 0
	for i in goal_word:
		if i not in dic:
			dic.add(i)
	for j in guess:
		if j in dic:
			counter += 1
			dic.remove(j)
	return counter 

