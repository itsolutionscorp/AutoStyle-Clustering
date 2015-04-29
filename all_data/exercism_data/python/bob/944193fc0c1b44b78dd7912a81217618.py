#!/usr/bin/env python3

default_responses = {
    'generic': 'Whatever.',
    'question': 'Sure.',
    'yelling': 'Woah, chill out!',
    'empty': 'Fine. Be that way!',
}

class Bob(object):
    def __init__(self, responses=default_responses):
        self.responses = responses

    @staticmethod
    def is_yelling(phrase):
        """Unicode-sensitive yelling test.
        We test if all the letters that *can* change case don't when passed to
        upper -- meaning they are all uppercase"""

        caseable = ''.join(c for c in phrase if c.upper() != c.lower())
        if not len(caseable):
            return False
        return caseable.isupper()

    def hey(self, phrase):
        phrase = phrase.strip()
        if not len(phrase):
            return self.responses['empty']
        elif Bob.is_yelling(phrase):
            return self.responses['yelling']
        elif phrase.endswith('?'):
            return self.responses['question']
        else:
            return self.responses['generic']
