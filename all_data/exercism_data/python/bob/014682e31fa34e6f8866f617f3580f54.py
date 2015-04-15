# -*- coding: utf-8 -*-

class Bob(object):

    def hey(self, message):
        if self.__is_being_asked_a_question(message):
            return u'Sure.'
        elif self.__is_being_yelled_at(message):
            return u'Whoa, chill out!'
        elif self.__is_being_ignored(message):
            return u'Fine. Be that way!'
        else:
            return u'Whatever.'

    def __is_being_asked_a_question(self, message):
        if message.endswith('?') and not message[:-1].isupper():
            return True
        else:
            return False

    def __is_being_yelled_at(self, message):
        if message[:-1].isupper() or message.endswith('?'):
            return True
        else:
            return False

    def __is_being_ignored(self, message):
        if not message or message.isspace():
            return True
        else:
            return False



def hey(message):
    return Bob().hey(message)
