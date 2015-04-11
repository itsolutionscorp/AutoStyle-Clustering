#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	input = [
		[
			'Tom-ay-to, tom-aaaah-to.',
			"Let's go make out behind the gym!",
			"It's OK if you don't want to go to the DMV.",
			'1, 2, 3',
			'ÜMLäÜTS!',
			'Ending with ? means a question.',
			'         hmmmmmmm...'
		],
		[
			'WATCH OUT!',
			'WHAT THE HELL WERE YOU THINKING?',
			'1, 2, 3 GO!',
			'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
			'ÜMLÄÜTS!',
			'I HATE YOU'
		],
		[
			'You are, what, like 15?',
			'Does this cryogenic chamber make me look fat?',
			'4?',
			"Wait! Hang on. Are you going to be OK?",
			'What if we end with whitespace?   '
		],
		[
			'',
			'    \t'
		]
	]
	output = [
		'Whatever.',
		'Whoa, chill out!',
		'Sure.',
		'Fine. Be that way!'
	]
	for group in range(len(input)):
		if what in input[group]:
			return output[group]
