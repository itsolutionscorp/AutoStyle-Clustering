class Bob:
    def hey(self, message):
        if self.__is_silence(message):
            return 'Fine. Be that way.'
        if self.__is_shouting(message):
            return 'Woah, chill out!'
        if self.__is_question(message):
            return 'Sure.'
        return 'Whatever.'

    def __is_silence(self, message):
        return message == ''

    def __is_shouting(self, message):
        return message.upper() == message

    def __is_question(self, message):
        return message.endswith('?')
