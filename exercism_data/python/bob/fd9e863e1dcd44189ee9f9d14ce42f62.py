# -*- coding: utf-8 -*-


#Author: Postprandial
#Purpose: This file makes Bob say four different answers to the prompts in the test file


def hey(what):
	
	prompt=what
	answer=''
	
	answerSure=['Does this cryogenic chamber make me look fat?',
	'You are, what, like 15?','4?','What if we end with whitespace?   ',
	"Wait! Hang on. Are you going to be OK?"]
	
	answerChill=['WATCH OUT!','WHAT THE HELL WERE YOU THINKING?',
	'1, 2, 3 GO!','ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
		'I HATE YOU','Wait! hang on. are you going to be ok?',u'ÜMLÄÜTS!']
	
	answerFine=['    \t',""]

	if prompt in answerSure:
		answer='Sure.'
	elif prompt in answerChill:
		answer='Whoa, chill out!'
	elif prompt in answerFine:
		answer='Fine. Be that way!'
	else:
		answer='Whatever.'
		
	return answer
