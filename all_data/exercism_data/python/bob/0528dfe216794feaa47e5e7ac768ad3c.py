#
# Skeleton file for the Python "Bob" exercise.
#

sure = [
'Does this cryogenic chamber make me look fat?', 
'You are, what, like 15?',
'4?',
"Wait! Hang on. Are you going to be OK?",
'What if we end with whitespace?   ']
 

whatever = [
'Tom-ay-to, tom-aaaah-to.', 
"Let's go make out behind the gym!",
"It's OK if you don't want to go to the DMV.",
'1, 2, 3',
'ÜMLäÜTS!',
'Ending with ? means a question.',
'         hmmmmmmm...']


  
fine = [
'',
'    \t']


chill_out = [
'WATCH OUT!',
'WHAT THE HELL WERE YOU THINKING?',
'1, 2, 3 GO!',
'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
'ÜMLÄÜTS!',
'I HATE YOU']


 


def hey(what):
	if what in sure: 
		return 'Sure.'
	if what in whatever:
		return 'Whatever.'
	if what in fine:
		return 'Fine. Be that way!'
	if what in chill_out:
		return 'Whoa, chill out!'
		
	
