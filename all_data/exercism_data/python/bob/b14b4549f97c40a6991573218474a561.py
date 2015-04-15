class Bob:
    """Bob"""

    def hey(self, message):
        """answers questions"""

        if (self.__isMeaningless(message)): return 'Fine. Be that way.'
        if (self.__isAngry(message)): return 'Woah, chill out!'
        if (self.__isQuestion(message)): return 'Sure.'

        return 'Whatever.'

    def __isMeaningless(self, message):
        """test whether the message is empty"""

        return message is None or message.strip() == ''

    def __isAngry(self, message):
        """test whether the message is all uppercase"""

        return message.upper() == message

    def __isQuestion(self, message):
        """test whether the message is a Question (ends with a ?)"""

        return message.find('?') == len(message) - 1
