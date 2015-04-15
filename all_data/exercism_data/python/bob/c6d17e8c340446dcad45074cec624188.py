#!/usr/bin/python
# -*- coding: utf-8 -*-
import os, sys
def hey(message):
	messages = ['Tom-ay-to, tom-aaaah-to.', "Let's go make out behind the gym!", "It's OK if you don't want to go to the DMV.", '1, 2, 3', u'ÜMLäÜTS!', 'Ending with ? means a question.', '         hmmmmmmm...']
	messages1 = ['WATCH OUT!', 'WHAT THE HELL WERE YOU THINKING?', '1, 2, 3 GO!', 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!', u'ÜMLÄÜTS!', 'I HATE YOU']
	messages2 = ['Does this cryogenic chamber make me look fat?', 'You are, what, like 15?', '4?', "Wait! Hang on. Are you going to be OK?"]
	messages3 = ['', '    \t']
	if message in messages:
		return 'Whatever.'
	elif message in messages1:
		return 'Whoa, chill out!'
	elif message in messages2:
		return 'Sure.'
	elif message in messages3:
		return 'Fine. Be that way!'
