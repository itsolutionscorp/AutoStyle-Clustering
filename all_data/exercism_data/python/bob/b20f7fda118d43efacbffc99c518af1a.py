#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re


class Bob(object):

    RESPONSES = (
        (re.compile(u"\s*$"), "Fine. Be that way!"),
        (re.compile(u"\d+\?$"), "Sure."),
        (re.compile(u"[0-9A-ZÆØÅÁÉÍÓÚÝÀÈÌÒÙỲÂÊÎÔÛŶÄËÏÖÜŸÃẼĨÕŨỸ\W]+[A-ZÆØÅÁÉÍÓÚÝÀÈÌÒÙỲÂÊÎÔÛŶÄËÏÖÜŸÃẼĨÕŨỸ\W]$", re.UNICODE), "Woah, chill out!"),
        (re.compile(u".*\?$"), "Sure."),
    )

    def hey(self, challenge):
        for pattern, response in self.RESPONSES:
            if pattern.match(challenge):
                return response
        return "Whatever."
