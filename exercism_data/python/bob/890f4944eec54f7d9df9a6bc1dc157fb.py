__author__ = 'shammas'

class Bob:
    
    @staticmethod
    def hey(msg=''):
        if msg == '' or msg.isspace():
            return 'Fine. Be that way!'
        if msg.isupper():
            return 'Woah, chill out!'
        elif msg[-1] == '?':
            return 'Sure.'
        return 'Whatever.'
