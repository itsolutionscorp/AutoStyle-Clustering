def is_shout (msg):
    return msg.isupper()


def is_silent (msg):
    return len(msg) == 0 or msg is None


def is_question (msg):
    return msg.endswith("?")


class Bob:
    def hey(self, msg):
        if is_silent(msg):
            return 'Fine, be that way.'
        if is_shout(msg):
            return 'Woah, chill out!'
        if is_question(msg):
            return 'Sure.'
        return 'Whatever.'
