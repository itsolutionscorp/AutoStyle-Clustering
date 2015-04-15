class Bob(object):

    @staticmethod
    def hey(text):
        text = text.strip() if text else ''
        if not text:
            return 'Fine. Be that way!'
        if text == text.upper():
            return 'Woah, chill out!'
        elif text.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
