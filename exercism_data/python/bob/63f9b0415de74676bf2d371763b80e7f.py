class Bob(object):
    def hey(self, message):
        if message is None or not message.strip():
            return 'Fine. Be that way!'

        elif self.allcaps(message):
            return 'Woah, chill out!'

        elif self.is_question(message):
            return 'Sure.'

        return 'Whatever.'

    def is_question(self, message):
        return message.endswith('?')

    def allcaps(self, message):
        alphas = [c for c in message if c.isalpha()]
        caps = all([c.isupper() for c in alphas])
        return alphas and caps
