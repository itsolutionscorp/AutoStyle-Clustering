import string


class Bob(object):

    def hey(self, msg):

        if _is_empty(msg):
            return "Fine. Be that way!"

        if _is_yell(msg):
            return "Woah, chill out!"

        if _is_question(msg):
            return "Sure."

        return 'Whatever.'


def _is_empty(msg):
    return msg.strip() == ''


def _is_yell(msg):
    return msg.upper() == msg and _has_letters(msg)


def _has_letters(msg):
    return any(character in string.letters
               for character in msg)


def _is_question(msg):
    return msg.endswith('?')
