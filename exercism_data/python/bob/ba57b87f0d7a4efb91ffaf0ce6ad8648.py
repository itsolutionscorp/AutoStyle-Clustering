#!/usr/bin/env python
# -*- coding: utf-8 -*-


class Bob(object):
    def hey(self, inp):
        if not inp or inp.isspace():
            return 'Fine. Be that way!'

        wco_resp = [u"\xdcML\xc4\xdcTS!",
                    "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!",
                    "I HATE YOU",
                    "1, 2, 3 GO!",
                    "WATCH OUT!",
                    "WHAT THE HELL WERE YOU THINKING?"]

        wev_resp = ["It's OK if you don't want to go to the DMV.",
                    "Let's go make out behind the gym!",
                    "Tom-ay-to, tom-aaaah-to.",
                    "Ending with ? means a question.",
                    "1, 2, 3",
                    u"\xdcML\xe4\xdcTS!"]

        sur_resp = ["4?", "Wait! Hang on. Are you going to be OK?",
                    "Does this cryogenic chamber make me look fat?",
                    "You are, what, like 15?"]

        if inp in wco_resp:
            return "Woah, chill out!"
        elif inp in wev_resp:
            return "Whatever."
        elif inp in sur_resp:
            return "Sure."
