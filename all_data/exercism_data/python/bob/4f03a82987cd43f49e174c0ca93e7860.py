import string

def has_letters(phrase):
	for c in string.ascii_letters:
		if c in phrase:
			return True
	return False

def yelling(phrase):
	if has_letters(phrase) and phrase == phrase.upper():
		return True
	else:
		return False

def question(phrase):
	if len(phrase) > 0 and "?" == phrase[-1]:
		return True
	else:
		return False

def silence(phrase):
	for c in phrase:
		if c not in string.whitespace:
			return False
	return True

def hey(phrase):
	if yelling(phrase):
		return "Whoa, chill out!"
	elif question(phrase):
		return "Sure."
	elif silence(phrase):
		return "Fine. Be that way!"
	else:
		return "Whatever."

if __name__ == "__main__":
	phrase = str(raw_input(">>> "))
	while phrase != "q":
		print hey(phrase)
		phrase = str(raw_input(">>> "))
