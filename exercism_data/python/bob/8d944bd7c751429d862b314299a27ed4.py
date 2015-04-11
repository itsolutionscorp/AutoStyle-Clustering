def hey(string):

	# catch all answer that sticks if no other cases are fulfilled

	answer = 'Whatever.'

	# case for 'addressing bob without saying anything'
	# if this is the case, no other cases can be true, so return the value instead of wasting cycles

	if string.isspace() or len(string) < 1:
		answer = 'Fine. Be that way!'
		return answer

	if string.endswith('?'):
		answer = 'Sure.'

	# per the test cases, yelling is more important than asking a question
	# therefore we need to make sure that the yelling case comes after the question case
	# so that it will override the previous value

	if string.isupper():
		answer = 'Whoa, chill out!'

	return answer
