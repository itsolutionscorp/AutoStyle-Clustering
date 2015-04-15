def hey(sentence):

	if questTest(sentence) == True:
		return "Sure."
	elif yellTest(sentence) == True:
		return "Whoa, chill out!"
	elif nothingTest(sentence) == True:
		return "Fine. Be that way!"
	else:
		return "Whatever."

#I ran the tests by returning False if the met any criteria that disqualified it from each category, and only returning
# True if it didn't fail them.
def questTest(s):
	#This first tests if the string has any letters or numbers, then if the last element is a question mark, then if
	# the sentence in uppercase is the same as it in lowercase and checking if it has any letters. Otherwise, it returns True.
	if any(c.isalpha() for c in s) == False and any(c.isdigit() for c in s) == False:
		return False
	elif s[-1] != "?":
		return False
	elif s.upper() == s and any(c.isalpha() for c in s) == True:
		return False
	else:
		return True

def yellTest(s):
	#This tests if the sentence is a all in uppercase, then if it contains any letters. Otherwise, it returns True.
	if s.upper() != s:
		return False
	elif any(c.isalpha() for c in s) == False:
		return False
	else:
		return True

def nothingTest(s):
	#I wrote this test in the opposite way, since it has such a strict inclusion criteria. It only asks if the string has
	#any letters or numbers. If it doesn't, it doesn't qualify.
	if any(c.isalpha() for c in s) == False and any(c.isdigit() for c in s) == False:
		return True
	else:
		return False
