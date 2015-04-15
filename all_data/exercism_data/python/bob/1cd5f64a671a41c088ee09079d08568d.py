class Bob(object):
    def hey(self, question):
        result = u'Whatever.'
        if question.endswith('?'):
            result = u'Sure.'
        if question.isupper():
            result = u'Woah, chill out!'
        if question.isspace() or len(question) == 0:
            result = u'Fine. Be that way!'
        return result
