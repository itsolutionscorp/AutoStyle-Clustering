class Bob:

    def _is_shouting(self, banter):
        lowers = [c for c in set(banter) if c.islower()]
        uppers = [c for c in set(banter) if c.isupper()]
        if uppers:
            if lowers:
                return False
            else:
                return True
        else:
            return False

    def _is_question(self, banter):
        if banter[-1] is '?':
            return True
        else:
            return False

    def _is_something(self, banter):
        if banter.strip():
            return True
        else:
            return False 

    def hey(self, banter):

        if self._is_something(banter):
            if self._is_shouting(banter):
                return 'Woah, chill out!'

            if self._is_question(banter):
                return 'Sure.'
            else:   
                return 'Whatever.'
        else:
            return 'Fine. Be that way!'
