#!/usr/bin/env python
# -*- coding: utf-8 -*-


class Bob(object):
    def hey(self, input_):
        """ a different way of checking for conditions """

        # we're still going to be using empty responses
        # as 'Fine. Be that way!'
        if not input_ or input_.isspace():
            return 'Fine. Be that way!'

        # now, this is the caps (shout in this case) check
        if input_.isupper():
            return 'Woah, chill out!'

        # what about questions?
        if input_.endswith('?'):
            return 'Sure.'

        # group others as whatever
        return 'Whatever.'
