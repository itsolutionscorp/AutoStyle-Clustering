""" Solution for exercism/python/bob """

class Bob(object):
    """
    Implementation of a lackadaisical teenager named bob.
    """

    def hey(self, message):
        # Anwering a simple 'hey'
        if not message:
            return 'Fine, be that way.'

        # Answer questions
        elif message[-1] == '?':
            return 'Sure.'

        # Respond to yelling
        elif message.isupper():
            return 'Woah, chill out!'

        # Handle unexpected messages
        else:
            return 'Whatever.'
