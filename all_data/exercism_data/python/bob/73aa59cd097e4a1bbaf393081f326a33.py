# -*- coding: utf-8 -*-

from __future__ import unicode_literals
import re

def hey(prompt):
    if prompt.strip() == '':
        return "Fine. Be that way!"

    regex = re.compile(r'[0-9,]')
    cleaned_prompt = regex.sub('', prompt)
    cleaned_prompt = cleaned_prompt.strip()
    regex_word = re.compile(r'[A-Za-z]+')
    contains_words = regex_word.match(cleaned_prompt)

    if cleaned_prompt.isupper():
        return "Whoa, chill out!"

    try:
        if cleaned_prompt[-1] == '?':
            return "Sure."
    except IndexError:
        pass

    return "Whatever."
