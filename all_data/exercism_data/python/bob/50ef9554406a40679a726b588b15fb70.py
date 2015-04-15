class Bob:
    def hey(self, x):
        if x is None or x.strip() == '':
            return 'Fine. Be that way!'
        if x == x.upper() and x != x.lower(): 
            return 'Woah, chill out!'
        if x.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
