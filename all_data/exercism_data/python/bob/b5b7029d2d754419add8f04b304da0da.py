class Bob:
    """Bob"""

    __responses = {
        'meaningLess': 'Fine. Be that way.',
        'angry': 'Woah, chill out!',
        'question': 'Sure.',
        'default': 'Whatever.'
    }

    def hey(self, message):
        """answers questions"""

        if (self.__isMeaningless(message)): return self.__responses['meaningLess']
        if (self.__isAngry(message)): return self.__responses['angry']
        if (self.__isQuestion(message)): return self.__responses['question']

        return self.__responses['default']

    def __isMeaningless(self, message):
        """test whether the message is empty"""

        return message is None or message.strip() == ''

    def __isAngry(self, message):
        """test whether the message is all uppercase"""

        return message.upper() == message

    def __isQuestion(self, message):
        """test whether the message is a Question (ends with a ?)"""

        return message.rfind('?') == len(message) - 1
