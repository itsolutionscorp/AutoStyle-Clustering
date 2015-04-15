def silence(message):
    return message.strip() == ""


def shouting(message):
    return message.isupper()


def question(message):
    return message[-1] == '?'


def statement(message):
    return True


class Bob(object):
    ANSWERS = [
        (silence, "Fine. Be that way!"),
        (shouting, "Woah, chill out!"),
        (question, "Sure."),
        (statement, "Whatever.")
    ]

    def hey(self, message):
        for answer_is_right_for, answer in self.ANSWERS:
            if answer_is_right_for(message):
                return answer
