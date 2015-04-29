# -*- coding: utf-8 -*-
"""
Bob the Bored Bard
==================

This module allows you to talk to bob. Warning: bob's vocabulary is pretty
limited.
"""

class Bob(object):

    def __init__(self):
        """Inits bob's responses"""
        self.responses = {
            'question': 'Sure.',
            'caps': 'Woah, chill out!',
            'empty': 'Fine. Be that way!',
            'default': 'Whatever.'
        }

    def check_upper(self, phrase):
        """
        Check phrase for uppercase

        Parameters
        ----------
        phrase: string
            Phrase to be checked for uppercase

        Output
        ------
        Boolean:
        True if If the entire phrase is uppercase
        False if any lowercase found in phrase
        """
        is_upper = True
        has_alpha = False

        for character in phrase:
            if character.isalpha():
                has_alpha = True
                if not character.isupper():
                    is_upper = False

        if not has_alpha:
            return False
        else:
            return is_upper


    def hey(self, query):
        """
        Bob's listener. Say hey to bob, and he will be somewhat conversational.

        Parameters
        ----------
        input: string
            Query for bob

        Output
        ------
        String, bob's response
        """
        stripped = query.strip()

        if stripped and self.check_upper(query):
            return self.responses['caps']

        if stripped and query[-1] == '?':
            return self.responses['question']

        if len(stripped) == 0:
            return self.responses['empty']

        return 'Whatever.'
