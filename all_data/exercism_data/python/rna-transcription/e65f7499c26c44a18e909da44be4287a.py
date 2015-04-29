class Bob(object):
    """A lackadaisical teenager that responds to conversation."""

    def hey(self, msg):
        """Return Bob's response to a given message."""

        if _is_silence(msg):
            return 'Fine. Be that way.'
        elif _is_yell(msg):
            return 'Woah, chill out!'
        elif _is_question(msg):
            return 'Sure.'
        else:
            return 'Whatever.'


def _is_silence(msg):
    return not msg.strip()

def _is_yell(msg):
    return msg.upper() == msg

def _is_question(msg):
    return msg.endswith('?')
