class Bob:
    def _blank(self, msg): return not msg or msg.isspace()
    def _shouting(self, msg): return msg.isupper()
    def _asking(self, msg): return msg.endswith('?')

    def hey(self, msg):
        if self._blank(msg):      return 'Fine. Be that way!'
        elif self._shouting(msg): return 'Woah, chill out!'
        elif self._asking(msg):   return 'Sure.'
        else:                     return 'Whatever.'
