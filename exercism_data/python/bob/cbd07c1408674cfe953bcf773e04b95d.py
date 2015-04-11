class Bob(object):
    """ A lackadaisical teenager named Bob """

    def hey(self, message):
        """ Respond to a verbal stimulus """
        # Empty stimulus
        if len(message.strip()) == 0:
            return "Fine. Be that way!"

        # Yelling
        if message.isupper():
            return "Woah, chill out!"

        # Questions
        if message.endswith('?'):
            return "Sure."

        return "Whatever."
