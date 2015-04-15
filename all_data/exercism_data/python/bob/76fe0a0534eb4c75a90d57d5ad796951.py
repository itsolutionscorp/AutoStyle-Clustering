import string
class Bob(object):
    def hey(self, talk):
        talk = unicode(talk)
        if talk is None or talk == '' or talk.isspace():
            return 'Fine. Be that way!'
        isQuestion = talk[-1:] == '?'  # Check this before replacing the punctuation characters
        translate_table = dict((ord(char), None) for char in unicode(string.punctuation + '0123456789'))
        talk = talk.translate(translate_table)
        isShouting = talk.upper() == talk and talk != '' and not talk.isspace()
        if not isShouting and isQuestion:
            return 'Sure.'
        if talk.upper() == talk and talk != '' and not talk.isspace():
            return 'Woah, chill out!'
        return 'Whatever.'
