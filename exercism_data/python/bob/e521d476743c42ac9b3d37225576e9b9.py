# -*- coding: utf-8 -*-

'''
Author: Postprandial
Purpose: Version2 of the Bob file:
Bob now answers all questions with 'Sure' and all shouting (caps) with 'Whoa, chill out!'
lowercase questions or questions ending in whitespace are also answered with 'sure'.
Bob also still looks 
All statements (upper & lowercase) are answered. 
'''

def hey(what):
	
	prompt=what.strip()
	answer=''
	
	answerFine=['    \t',""]

	if prompt in answerFine:
		answer='Fine. Be that way!'
	elif prompt.isupper():
		answer='Whoa, chill out!'
	elif prompt[-1]=='?':
		answer='Sure.'
	else:
		answer='Whatever.'
		
	return answer
