class Bob():
    def hey(self, q):

        if len(q.strip()) == 0:
            return 'Fine. Be that way!'

        if q.isupper():
            return 'Woah, chill out!'

        if q.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
