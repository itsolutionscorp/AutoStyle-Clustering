import re

class Bob:
    def hey(self, text):
        if text == '' or text.isspace():
           return 'Fine. Be that way!'
        if text == text.upper() and any(c.isalpha() for c in text):
            return 'Woah, chill out!'
        if text[-1] == '?':
            return 'Sure.'
        return 'Whatever.'


    def rehey(self, text):
        if re.search(r'^\s*$', text):
           return 'Fine. Be that way!'
        if re.search(r'[A-Z]', text) and text == text.upper():
            return 'Woah, chill out!'
        if re.search(r'\?\s*$', text):
        #if re.search(r'\?$', text):
            return 'Sure.'
        return 'Whatever.'
