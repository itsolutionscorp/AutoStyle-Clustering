# -*- coding: utf-8 -*-

def hey(str):
	whatever = ['Tom-ay-to, tom-aaaah-to.','Let\'s go make out behind the gym!','It\'s OK if you don\'t want to go to the DMV.','1, 2, 3',u'ÜMLäÜTS!','Ending with ? means a question.','         hmmmmmmm...']
	if str in whatever:
		return 'Whatever.'

	chillout = ['WATCH OUT!','WHAT THE HELL WERE YOU THINKING?','1, 2, 3 GO!' , u'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',u'ÜMLÄÜTS!','I HATE YOU']

	if str in chillout:
		return 'Whoa, chill out!'

	sure = ['Does this cryogenic chamber make me look fat?','You are, what, like 15?','4?' ,'Wait! Hang on. Are you going to be OK?']
	if str in sure:
		return 'Sure.'

	elif str == '' or str == '    \t':
		return 'Fine. Be that way!'
