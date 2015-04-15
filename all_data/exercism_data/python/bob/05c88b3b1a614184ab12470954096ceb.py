#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Steve Phillips / elimisteve
# 2013.12.16

class Bob(object):

    def hey(self, msg):
        msg = msg.strip()
        if msg == "":
            return 'Fine. Be that way!'

        if msg == msg.upper() and any(char.isalpha() for char in msg):
            return 'Woah, chill out!'

        if msg[-1] == '?':
            return 'Sure.'

        return 'Whatever.'
