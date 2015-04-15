# -*- coding: utf-8 -*-

QUESTION_ANSWER = 'Sure.'
YELL_ANSWER = 'Whoa, chill out!'
ANYTHING_ANSWER = 'Fine. Be that way!'
OTHER_ANSWER = 'Whatever.'

class Bob:
    def hey(self,s):
        if test_yell(s):
            return YELL_ANSWER
        elif test_question(s):
            return QUESTION_ANSWER
        elif test_anything(s):
            return ANYTHING_ANSWER
        else:
            return OTHER_ANSWER

def test_yell(s):
    return s.isupper()

def test_question(s):
    return (s[-1:]=="?")

def test_anything(s):
    return (len(s)==0 or s.isspace())
