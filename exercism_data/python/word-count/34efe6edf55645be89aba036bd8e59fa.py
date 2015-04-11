#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Reports the number of occurences of each word in text.
"""


def word_count(text):
    """Reports the number of occurences of each word in text."""

    words = {}
    text = text.translate(None, ".,:;!@#$%^&*()")
    for word in text.split():
        lcword = word.lower()
        if lcword in words:
            words[lcword] += 1
        else:
            words[lcword] = 1

    return words
