def hey(st):
	if len(st) == 0 or st.isspace():
		return 'Fine. Be that way!'
	elif ((st[-1] == '!' or st[-1] == '?') and st.isupper()) or st.isupper():
		return 'Whoa, chill out!'
	elif st[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
