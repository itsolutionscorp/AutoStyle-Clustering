class Bob:
    @staticmethod
    def hey(s):
        if len(s) == 0 or s.isspace():
            return 'Fine. Be that way!'
        if s.isupper():
            return 'Woah, chill out!' 
        if s.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
