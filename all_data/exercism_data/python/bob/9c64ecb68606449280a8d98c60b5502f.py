__author__ = 'a_beautiful_mind'

"""
Bob is a lackadaisical teenager. In conversation, his responses are very limited.
Bob answers 'Sure.' if you ask him a question.
He answers 'Woah, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him without actually saying anything.
He answers 'Whatever.' to anything else.
"""

# Bob helper functions


def is_silent(message):
    """
    Check if the message is empty
    :param message: message
    :return: True if the message is empty otherwise False
    """
    return not message.strip()


def is_yelling(message):
    """
    Check if the message is like yelling (assuming ALL CAPS is like shouting)
    :param message: message
    :return: True if the message is like yelling, otherwise False
    """
    return message.isupper()


def is_interrogative(message):
    """
    Check if message is interrogative
    :param message: message
    :return: True if message is interrogative and is not empty excluding the question mark,
    otherwise False
    """
    return message.rstrip().endswith('?') and message.replace('?', '').strip()


# Bob class


class Bob(object):
    def hey(self, message=''):
        """
        Return Bob's answer to the message
        :param message: message
        :raise ValueError: if the message is not a string
        :return: Bob's answer
        """
        if not isinstance(message, basestring):
            raise ValueError('not a string')

        if is_silent(message):
            answer = 'Fine. Be that way!'
        elif is_yelling(message):
            answer = 'Woah, chill out!'
        elif is_interrogative(message):
            answer = 'Sure.'
        else:
            answer = 'Whatever.'
        return answer
