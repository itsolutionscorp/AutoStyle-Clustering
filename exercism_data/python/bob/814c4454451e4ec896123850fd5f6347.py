import re
class Bob:
    def hey(self, message):
        if message.isupper(): 
            return 'Woah, chill out!'
        if message.endswith('?'):
             return 'Sure.'
        if not message.strip():
             return 'Fine. Be that way!'
        return 'Whatever.'
