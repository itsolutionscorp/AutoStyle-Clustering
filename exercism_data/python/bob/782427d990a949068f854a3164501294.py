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
        if msg.endswith('?'):
            return True
        else:
            return False

    def _isYell(self, msg):
        if msg.isupper():
            return True
        else:
            return False

    def _isNothing(self, msg):
        if len(msg.strip()) == 0:
            return True
        else:
            return False
