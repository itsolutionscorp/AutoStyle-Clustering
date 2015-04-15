# -*- coding: utf-8 -*-
"""Module implements various personalities of various persons in context
of normal human communication.
"""
import re
from abc import ABCMeta, abstractmethod


class Person(object):
    """Implements a basic interface which describes common behaviours
    needed by every personality.
    """
    __metaclass__ = ABCMeta

    @abstractmethod
    def hey(self, quote):
        """Used as an responder in communication

        :param quote: quote passed by peer
        :return: empty string.
        """
        return ''


class Bob(Person):
    """Implements personality of a average """

    #:A list of reaction validators. If quote from your peer applies
    #:to one of them, then Bob responds in a selected way.
    reactions = (
            (r'^( )*$', 'Fine. Be that way!'),
            (r'^([^a-z]*|!)$', 'Woah, chill out!'),
            (r'^(.*)\?$', 'Sure.'),
    )

    default_reaction = 'Whatever.'

    def hey(self, quote):
        """Implements interaction interface for Bob."""
        for reaction_check, response in self.reactions:
            if re.compile(reaction_check).match(quote or ''):
                return response
        return self.default_reaction


Person.register(Bob)
