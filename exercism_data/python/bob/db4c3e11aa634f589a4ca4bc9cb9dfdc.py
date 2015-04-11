__author__ = 'angelo'
import unicodedata as ud


class Bob():

    def hey(self, message):
        message = unicode(message.strip())
        print message
        if message == '':
            return 'Fine. Be that way!'
        elif all([ud.category(x) == "Lu" for x in message if ud.category(x)[0] == "L"]) and any([ud.category(x)[0] == "L" for x in message]):
            return 'Woah, chill out!'
        elif message[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
