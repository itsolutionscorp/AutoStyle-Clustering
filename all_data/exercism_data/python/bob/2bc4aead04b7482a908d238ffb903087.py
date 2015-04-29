__author__ = 'jos'


class Bob():
    def hey(self, sentence):

        """
        Used by the unit tests to get a response
        :param sentence: string to which bob should respond
        :return: the response as a string
        """
        if is_yell(sentence):
            return 'Woah, chill out!'
        elif is_question(sentence):
            return 'Sure.'
        elif is_nothing_said(sentence):
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'


def is_yell(sentence):
    """
    Test if the sentence is being yelled
    :param sentence: string to be tested
    :return: boolean
    """
    assert isinstance(sentence, basestring)

    return sentence.isupper()


def is_nothing_said(sentence):
    """
    Test if the sentence is "empty"
    :param sentence: string to be tested
    :return: boolean
    """
    assert isinstance(sentence, basestring)

    return sentence.strip() == ''


def is_question(sentence):
    """
    Test is the sentence is a question

    :param sentence: string to be tested
    :return: boolean
    """
    assert isinstance(sentence, basestring)

    return sentence[-1:] == '?' #last character
