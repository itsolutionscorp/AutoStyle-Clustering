class Bob:
    def hey(self, msg):
        reply = 'Whatever.'
        if self._isYell(msg):
            reply = 'Woah, chill out!'
        elif self._isQuestion(msg):
            reply = 'Sure.'
        elif self._isNothing(msg):
            reply = 'Fine. Be that way!'
        return reply
    
    def _isQuestion(self, msg):
        return msg.endswith('?')

    def _isYell(self, msg):
        return msg.isupper()

    def _isNothing(self, msg):
        return len(msg.strip()) == 0
