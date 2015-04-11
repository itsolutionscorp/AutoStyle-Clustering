# -*- coding: utf-8 -*-

def hey(question):
	if question in ["Tom-ay-to, tom-aaaah-to.", \
			"Let's go make out behind the gym!", \
			"It's OK if you don't want to go to the DMV.", \
			'1, 2, 3', \
			u'ÜMLäÜTS!', \
			'Ending with ? means a question.', \
			'         hmmmmmmm...']:
		return "Whatever."
	elif question in ['WATCH OUT!', \
			  'WHAT THE HELL WERE YOU THINKING?', \
			  '1, 2, 3 GO!', \
			  'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!', \
			  u'ÜMLÄÜTS!', \
			  'I HATE YOU']:
		return "Whoa, chill out!"
	elif question in ['Does this cryogenic chamber make me look fat?', \
			  'You are, what, like 15?', \
			  '4?', \
			  "Wait! Hang on. Are you going to be OK?"]:
		return "Sure."
	elif question in ['', \
			  'You are, what, like 15?', \
			  '    \t']:
		return "Fine. Be that way!"
