# Dictionnary: sentence_type -> answer
answers = {
	'question': 'Sure.',
	'yell': 'Whoa, chill out!',
	'nothing': 'Fine. Be that way!',
	'anything_else': 'Whatever.'
}

def hey(sentence):
	sentence_type = analyze(sentence)
	return answers[sentence_type]

def analyze(sentence):
	sentence = sentence.strip()
	if sentence == '':
		sentence_type = 'nothing'
	elif sentence.isupper():
		sentence_type = 'yell'
	elif sentence[-1] == '?':
		sentence_type = 'question'
	else:
		sentence_type = 'anything_else'

	return sentence_type
