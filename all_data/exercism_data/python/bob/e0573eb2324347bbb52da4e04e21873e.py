class Bob:
    @staticmethod
    def hey(s):
        s = s.strip()
        if len(s) == 0 or s.isspace():
            return 'Fine. Be that way!'
        if s.isupper():
            return 'Woah, chill out!'
        if s.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
