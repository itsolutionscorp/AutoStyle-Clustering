RESPONSETO = {
	'question': 'Sure.',
	'shouting': 'Whoa, chill out!',
	'nothing': 'Fine. Be that way!',
	'other': 'Whatever.'
	}

def main():
	what_I_say = input('Say something to Bob:')
	while not (what_I_say.lower() == 'exit'):
		if (what_I_say.lower().startswith('exit') or 
			what_I_say.lower().startswith('quit')):
			print('Type "exit" to leave Bob alone.')
		else:
			response = hey(what_I_say)
			print(response)
		what_I_say = input('Say something else to Bob:')
	print('Whatever, bye.')

def hey(what_I_say):
	if what_I_say.strip() == '':
		return(RESPONSETO['nothing'])
	elif what_I_say.isupper():
		return(RESPONSETO['shouting'])
	elif what_I_say.endswith('?'):
		return(RESPONSETO['question'])
	else:
		return(RESPONSETO['other'])
	
if __name__ == '__main__':
	main()
