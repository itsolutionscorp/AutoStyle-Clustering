# Basic 'if, else' tree
def bob(sentence):
# Saying nothing makes Bob say 'Fine, be that way'
	if len(sentence) == 0:
		return 'Fine, be that way!'
# Asking a question makes Bob say 'Sure.'
	elif sentence[len(sentence) - 1] == '?':
		return 'Sure.'
# Shouting causes Bob to say 'Whoa, chill out!'
	elif sentence[len(sentence) - 1] == '!':
		return 'Whoa, chill out!'
# Saying anything else makes Bob say 'Whatever'
	else:
		return 'Whatever.'
print bob(raw_input('What would you like to say to Bob?: '))
