class Bob:
    """Bob is a lackadaisical teenager.

    In conversation, his responses are very limited.

    """

    def hey(self, message):
        """Talk to Bob."""
        if not message:  # silence
            return 'Fine, be that way.'
        if message[-1] == '?':  # question
            return 'Sure.'
        if message.upper() == message:  # all uppercase: shouting
            return 'Woah, chill out!'
        return 'Whatever.'  # everything else
