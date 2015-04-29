class Message(object):
    def __init__(self, message):
        self.message = message

    def is_question(self):
        return self.message.endswith("?")

    def is_yelling(self):
        return self.message.isupper()

    def is_shut_up(self):
        return self.message is None or not self.message.strip()


class Bob(object):
    def hey(self, msg):
        message = Message(msg)
        if message.is_shut_up():
            return 'Fine. Be that way!'
        if message.is_yelling():
            return 'Woah, chill out!'
        if message.is_question():
            return "Sure."
        return "Whatever."
