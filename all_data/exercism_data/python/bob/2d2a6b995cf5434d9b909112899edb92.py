class Bob:
    """Bob"""

    __meaningLessResponse = 'Fine. Be that way.'
    __angryResponse = 'Woah, chill out!'
    __questionResponse = 'Sure.'
    __defaultResponse = 'Whatever.'

    def hey(self, message):
        """answers questions"""

        if (self.__isMeaningless(message)): return self.__meaningLessResponse
        if (self.__isAngry(message)): return self.__angryResponse
        if (self.__isQuestion(message)): return self.__questionResponse

        return self.__defaultResponse

    def __isMeaningless(self, message):
        """test whether the message is empty"""

        return message is None or message.strip() == ''

    def __isAngry(self, message):
        """test whether the message is all uppercase"""

        return message.upper() == message

    def __isQuestion(self, message):
        """test whether the message is a Question (ends with a ?)"""

        return message.rfind('?') == len(message) - 1
