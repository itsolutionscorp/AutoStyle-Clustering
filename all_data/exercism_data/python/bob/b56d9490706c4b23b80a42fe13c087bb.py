# -*- coding: utf-8 -*-

from __future__ import unicode_literals


class Bob:
    def hey(self, input):

        if input.isupper() is True:
            return 'Whoa, chill out!'

        elif len(input) > 1 and input[len(input) - 1] == "?":
            return 'Sure.'


        elif len(input) < 1:
            return 'Fine. Be that way!'

        else:
            return 'Whatever.'
