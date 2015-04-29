class Bob(object):
    def hey(self, incoming):
        if not incoming or not incoming.strip():
            return 'Fine. Be that way!'

        decoded = incoming.decode('latin-1')

        is_shouting = decoded.isupper()
        is_question = decoded.endswith('?')

        if is_shouting:
            return 'Woah, chill out!'

        if is_question:
            return 'Sure.'

        return 'Whatever.'
