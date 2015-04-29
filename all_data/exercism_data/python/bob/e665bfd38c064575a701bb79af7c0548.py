#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re


class Bob(object):

    RESPONSES = (
        (lambda x: not x.strip(), "Fine. Be that way!"),
        (lambda x: x.isupper(), "Woah, chill out!"),
        (lambda x: x.endswith("?"), "Sure."),
    )

    def hey(self, challenge):
        for test, response in self.RESPONSES:
            if test(challenge):
                return response
        return "Whatever."
