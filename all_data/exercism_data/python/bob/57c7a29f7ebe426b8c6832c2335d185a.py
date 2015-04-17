from re import search


class Bob(object):
    ANSWERS = [
        ("silence", "Fine. Be that way!"),
        ("shouting", "Woah, chill out!"),
        ("question", "Sure."),
        ("statement", "Whatever.")
    ]

    def hey(self, message):
        for key, answer in self.ANSWERS:
            if getattr(self, key)(message):
                return answer

    def silence(self, message):
        return message.strip() == ""

    def shouting(self, message):
        return self.all_upcase(message) and self.has_letters(message)

    def question(self, message):
        return message[-1] == '?'

    def statement(self, message):
        return True

    def all_upcase(self, message):
        return message == message.upper()

    def has_letters(self, message):
        return search('[a-zA-Z]', message)