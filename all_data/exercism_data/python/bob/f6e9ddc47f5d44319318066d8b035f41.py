def silence(message):
    return (len(message) == 0 or message.isspace()) and "Fine. Be that way!"


def shouting(message):
    return message.isupper() and "Woah, chill out!"


def question(message):
    return message[-1] == '?' and "Sure."


def statement():
    return "Whatever."


class Bob(object):

    def hey(self, message):
        return silence(message) or \
            shouting(message) or \
            question(message) or \
            statement()
