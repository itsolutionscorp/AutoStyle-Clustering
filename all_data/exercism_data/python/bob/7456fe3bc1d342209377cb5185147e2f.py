class Bob:
    def __is_question(self, message):
        return message.endswith('?')

    def __is_shouting(self, message):
        return message.upper() == message

    def hey(self, message):
        if message is None or not message:
            return 'Fine. Be that way.'

        if self.__is_shouting(message):
            return 'Woah, chill out!'

        if self.__is_question(message):
            return 'Sure.'

        return 'Whatever.'
