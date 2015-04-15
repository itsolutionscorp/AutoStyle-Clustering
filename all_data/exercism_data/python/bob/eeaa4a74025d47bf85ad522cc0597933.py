# Talking to Bob the teenager with Python
# Python 2.7.8


say_to_bob = raw_input('What would you like to say to Bob? : ')


if say_to_bob.endswith('?'):
	print 'Sure'
	
elif say_to_bob.endswith('!'):
	print 'Whoa, chill out!'
	
elif not say_to_bob:
	print 'Fine. Be that way!'
	
else:
	print 'Whatever'
