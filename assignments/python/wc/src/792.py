import re
def word_count(phrase):
	phrase = phrase.lower()
	words = phrase.split()
	return {re.sub(r'\W*', '', word) : len(re.findall(r'\b' + re.sub(r'\W*', '', word) + r'\b', phrase)) for word in words if re.sub(r'\W*', '', word).isalnum() == True or re.sub(r'\W*', '', word).isalpha() == True}
print(word_count('car : carpet as java : javascript!!&@$%^&'))
