class Bob:
    def __init__(self):
        self.sure = 'Sure.'
        self.whatever = 'Whatever.'
        self.woah_chill_out = 'Woah, chill out!'
        self.fine__be_that_way = 'Fine. Be that way!'

    @staticmethod
    def question(message):
        return message.endswith("?")

    @staticmethod
    def uppercase(message):
        return (message == message.upper()) & (message != message.lower())

    @staticmethod
    def silent(message):
        return (len(message) == 0) | message.isspace()

    def hey(self, message):
        if self.uppercase(message):
            return self.woah_chill_out
        elif self.question(message):
            return self.sure
        elif self.silent(message):
            return self.fine__be_that_way
        else:
            return self.whatever
