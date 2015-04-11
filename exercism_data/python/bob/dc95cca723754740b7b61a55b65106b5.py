__author__ = 'Oniwa'


class Bob(object):
    def __init__(self):
        self.mlength = None

    def _number(self, msg):
        msg = msg.translate(None, ' ,')
        for char in msg:
            try:
                float(char)
            except ValueError:
                return False
        return True

    def _number_question(self, msg):
        if msg[self.mlength - 1] == '?':
            msg = msg.translate(None, '?')
            if self._number(msg):
                return True
        else:
            return False

    def hey(self, msg):
        self.mlength = len(msg)
        if msg == '':
            return 'Fine. Be that way!'
        elif type(msg) == unicode:
            if msg.upper() == msg:
                return 'Woah, chill out!'
            else:
                return 'Whatever.'
        elif msg.translate(None, ' ') == '':
            return 'Fine. Be that way!'
        elif self._number(msg):
            return 'Whatever.'
        elif self._number_question(msg):
            return 'Sure.'
        elif msg.upper() == msg:
            return 'Woah, chill out!'
        elif msg[self.mlength - 1] == '?':
            return 'Sure.'
        elif msg[self.mlength - 1] == '.':
            return 'Whatever.'
        elif msg[self.mlength - 1] == '!':
            return 'Whatever.'

if __name__ == '__main__':
    bob = Bob()
    print bob.hey('Dude!')
