import string


def detect_anagrams(input, guesses):
	answers = []
	for guess in guesses:
		if is_anagram(string.lower(input), string.lower(guess)):
			answers.append(guess)
	return answers
	
def is_anagram(input, guess):
	if len(input) is not len(guess):
		return False
	elif input == guess:
		return False
	insort = sorted(input)
	gusort =  sorted(guess)
	return insort == gusort
