class Bob(object):

    def hey(self, message):
        stripped_message = message.strip() if message else None

        if stripped_message:

            if stripped_message == self.shouting_message(stripped_message):
                return self.shouting_response()

            if self.question(stripped_message):
                return self.question_response()

            return self.default_response()
        else:
            return self.silence_response()

    def shouting_message(self, message):
        return message.upper()

    def question(self, message):
        return message.endswith('?')

    def shouting_response(self):
        return 'Woah, chill out!'

    def question_response(self):
        return 'Sure.'

    def silence_response(self):
        return 'Fine. Be that way!'

    def default_response(self):
        return 'Whatever.'
