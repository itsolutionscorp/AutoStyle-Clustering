"""Autostyle Study"""
def num_common_letters(a, b):
	letters_a = []
	letters_b = []
	count = 0
	for x in a:
		if x not in letters_a:
			letters_a.append(x)
		else:
			pass
	for x in b:
		if x not in letters_b:
			letters_b.append(x)
		else:
			pass
	for x in letters_a:
		if x in letters_b:
			count += 1
		else:
			pass
	return count


Positive Hints


Negative Hints


Approach Hints
Consider this approach: for each letter in the first word, think about how can you find whether it is also in the second word. To find whether it is also in the second word, you should only have to use a single line of code. Use the skeleton we've provided if you're stuck.

Skeleton
def num_common_letters(---------, -----):
    --------- = -
    --------- = []
    for - in list(---------):
        if - in list(-----) --- - --- in ---------:
            --------- += - 
            --------- += -
    return ---------    
