def hey(what):
#
# Bob's default response is "Whatever."
#
	response = 'Whatever.'
#
# An empty input, or one consisting only of white space, is "addressing
# Bob without actually saying anything".
#
	if what == '' or what.isspace():
		response = 'Fine. Be that way!'
# 
# "Yelling" means ALL CAPS but also SOME LETTERS IN THERE WHICH CAN
# ACTUALLY BE CAPITALIZED. This is an economical way to test.
#
	elif what == what.upper() and what != what.lower():
		response = 'Whoa, chill out!'
#
# Questions end with a question mark, but Bob responds to a "Forceful
# question" as though he were being yelled at, rather than being asked
# something.
#
	elif what.endswith('?'):
		response = 'Sure.'
	return response
