class Bob(object):
    """
    Bob the lackadaisical teenager.
    """
    def _respond(self, what):
        """
        Respond to a conversation.
        """
        if bool(what) == False or bool(what.strip()) == False:
            return 'Fine. Be that way!'
        if what == what.upper():
            return 'Woah, chill out!'
        if what[-1] == '?':
            return 'Sure.'
        return 'Whatever.'

    def __getattr__(self, _):
        """
        Allow Bob to respond in many ways.
        """
        return self._respond
