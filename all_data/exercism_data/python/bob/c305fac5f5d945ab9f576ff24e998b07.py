class Teenager:
    ANSWER_SURE = 'Sure.'
    ANSWER_CHILL = 'Whoa, chill out!'
    ANSWER_EMPTY = 'Fine. Be that way!'
    ANSWER_WHATEVER = 'Whatever.'

    def answer(self, sentence):
        if sentence.is_empty():
            return self.ANSWER_EMPTY

        if sentence.is_upcased():
            return self.ANSWER_CHILL

        if sentence.is_ended_with('?'):
            return self.ANSWER_SURE

        return self.ANSWER_WHATEVER


class Sentence:
    def __init__(self, str):
        self.__str = str

    def is_ended_with(self, letter):
        return self.__str[-1] == letter

    def is_upcased(self):
        return self.__str.isupper()

    def is_empty(self):
        return len(self.__str.strip()) == 0


def hey(str):
    sentence = Sentence(str)
    return Teenager().answer(sentence)
