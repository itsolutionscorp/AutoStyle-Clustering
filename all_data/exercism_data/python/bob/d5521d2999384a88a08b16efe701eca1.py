class Bob:
    def hey(self, s):
    	if s == None or s.strip() == '':
    		return 'Fine. Be that way.'

        if s == s.upper():
        	return 'Woah, chill out!'

        if s.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
