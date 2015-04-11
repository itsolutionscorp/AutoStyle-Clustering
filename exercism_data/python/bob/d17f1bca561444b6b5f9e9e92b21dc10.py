class Bob:
    """Bob imitates lackadaisical teenager."""

    def hey(self, hey):
        """Returns the response to message.

        Keyword arguments:
        hey -- the message you say to Bob.

        Bob responses "Sure" if you ask him a qeustion.
        Bob responses "Woah, chill out!" if you yell at him.
        Bob responses "Fine. Be that way!" if you address him
        withou actually saying anything.
        And he responses "Whatever." to enything else.
        """
        if not hey.strip():
            return 'Fine. Be that way!'
        elif hey.isupper():
            return 'Woah, chill out!'
        elif hey.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
