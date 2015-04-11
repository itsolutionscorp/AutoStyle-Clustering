class Bob(object):
    def __init__(self):
        pass

    def hey(self, message):
        # Respond to empty messages.
        if not message.strip():
            return 'Fine. Be that way!'

        # If the message is yelled, request to chill out.
        letters = ''.join(c for c in message if c.isalpha())
        if letters and letters == letters.upper():
            return 'Woah, chill out!'

        # If the message is a question, be agreeable.
        if message.endswith('?'):
            return 'Sure.'

        # Otherwise, we don't care.
        return 'Whatever.'
