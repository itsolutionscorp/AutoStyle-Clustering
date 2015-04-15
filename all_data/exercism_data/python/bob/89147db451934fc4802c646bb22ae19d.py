class Bob:
    def hey(self, q):
        if q == '' or q.isspace():
            return 'Fine. Be that way!'
        if q.isupper():
            return 'Woah, chill out!'
        if q[-1] == '?':
            return 'Sure.'
        return 'Whatever.'
