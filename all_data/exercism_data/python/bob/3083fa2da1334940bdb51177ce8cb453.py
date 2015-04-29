__all__ = ['Bob']


class BlankResponder(object):
    response = 'Fine, be that way.'

    def applicable_to(self, message):
        return not message.strip()


class ShoutingResponder(object):
    response = 'Woah, chill out!'

    def applicable_to(self, message):
        return message.isupper()


class QuestionResponder(object):
    response = 'Sure.'

    def applicable_to(self, message):
        return message.endswith('?')


class DefaultResponder(object):
    response = 'Whatever.'

    def applicable_to(self, message):
        return True


class Bob(object):
    responders = [
        BlankResponder(), ShoutingResponder(), QuestionResponder(),
        DefaultResponder(),
    ]

    def hey(self, message):
        for responder in self.responders:
            if responder.applicable_to(message):
                return responder.response
