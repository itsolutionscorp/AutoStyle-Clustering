def hey(s):
	if s == '' or '\t' in s or s == '\n':
		return 'Fine. Be that way!'
	if s[len(s)-1] == '?':
		return 'Sure.'
	if s[len(s)-1] == '!' or s.isupper():
		return 'Woah, chill out!'
	return 'Whatever.'
