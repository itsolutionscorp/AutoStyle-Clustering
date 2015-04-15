#!/usr/bin/env python3
# -*- coding: utf-8 -*-


def hey(message):
    message = message.strip()
    if message.isupper():
        return "Whoa, chill out!"
    elif message.endswith("?"):
        return "Sure."
    elif not message:
        return "Fine. Be that way!"
    else:
        return "Whatever."
