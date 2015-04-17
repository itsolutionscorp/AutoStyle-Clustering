import re
def word_count(phrase):
	counts = {}
	phrase = re.sub('[:!@#$%^&*(),]', '', phrase).lower()
	for word in phrase.split():
		if word not in counts: 
			counts[word] = 1
		else: 
			counts[word] += 1
	return counts
