# Bob module

#Still working this out :S
"""def check_spaces(phrase):
	count = 0
	for x in phrase:
		if count < 2:
			if x == " ":
				count += 1
			else:

				count = 0
"""			

def hey(x):

	x_length = len(x)
#	x_strip = x.strip()

	if x.isupper():
                return 'Whoa, chill out!'
	elif x[x_length-1:x_length] == "?":
		return 'Sure.'
#	Is this needed?
#	elif x == "":
#                return 'Fine. Be that way!'
	elif not x.strip():
		return 'Fine. Be that way!'
#	Didn't work
#	elif x == x_strip:
#                return 'Fine. Be that way!'
	else:
		return 'Whatever.'
