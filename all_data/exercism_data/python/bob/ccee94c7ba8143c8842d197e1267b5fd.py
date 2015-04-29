#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re


class Bob(object):

    RESPONSES = (
        (lambda x: re.match(u"\s*$", x), "Fine. Be that way!"),
        (lambda x: x.isupper(), "Woah, chill out!"),
        (lambda x: re.match(u".*\?$", x), "Sure."),
    )

    def hey(self, challenge):
        for test, response in self.RESPONSES:
            if test(challenge):
                return response
        return "Whatever."
