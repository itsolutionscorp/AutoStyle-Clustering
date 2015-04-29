class Bob(object):
    def hey(self, statement):
        answer = u'Whatever.'
        if statement.isupper():
            answer = u'Woah, chill out!'
        elif statement.endswith('?'):
            answer = u'Sure.'
        elif statement.isspace() or not statement:
            answer = u'Fine. Be that way!'
        return answer
