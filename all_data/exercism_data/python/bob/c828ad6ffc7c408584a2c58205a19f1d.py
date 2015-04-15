def _silent(message):
    return not message.strip()


def _yelling(message):
    return message.isupper()


def _querying(message):
    return message.endswith('?')


class Bob():
    def hey(self, message):
        message = message or ''

        if _silent(message):
            return 'Fine. Be that way!'
        if _yelling(message):
            return 'Woah, chill out!'
        if _querying(message):
            return 'Sure.'
        return 'Whatever.'
