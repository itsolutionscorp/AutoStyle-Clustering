def _is_silence(message):
    return not message or not message.strip()

def _is_yelling(message):
    return message and message.isupper()

def _is_question(message):
    return message and message.endswith("?")

class Bob(object):
    # Replies are in order of precedence.
    _replies = (
        (_is_silence, "Fine. Be that way!"),
        (_is_yelling, "Woah, chill out!"),
        (_is_question, "Sure."),
    )
    _other_reply = "Whatever."

    def hey(self, message):
        for matcher, reply in self._replies:
            if matcher(message):
                return reply
        return self._other_reply
