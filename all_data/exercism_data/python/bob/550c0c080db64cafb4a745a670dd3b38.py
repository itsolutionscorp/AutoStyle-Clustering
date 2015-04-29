import re

class Bob(object):
    def hey(self, incoming):
        if not incoming or not incoming.strip():
            return 'Fine. Be that way!'

        decoded = incoming.decode('latin-1')

        is_shouting = decoded == decoded.upper()
        has_letters = re.search(r'[^\W\d]', decoded)
        is_question = decoded.endswith('?')

        if is_shouting and has_letters:
            return 'Woah, chill out!'

        if is_question:
            return 'Sure.'

        return 'Whatever.'
