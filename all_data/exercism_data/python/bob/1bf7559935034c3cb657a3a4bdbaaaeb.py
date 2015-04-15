# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
import re   # Regex module

"""
General outline of solution:
Come up with a set of disjoint regular expressions
Store as a list of tuples, with the first being the raw regex, the second being the response

iterate over the list, match regex to strings; if no matches, pass through to fallback.

Theoretically, modifying the regexes then is the only really hard part.
"""


def hey(what):
    rules = [
    # I want to make these so that they're not order-dependent... It's hard, though.
        (r'^\s*$', "Fine. Be that way!"),    # Matches nothing being said
        (r'^[A-Z0-9\WÜÄ]+[!.]$|^[A-Z\s]+$|^[A-Z\s]+\?$', "Whoa, chill out!"),      # Matches yelling
        (r'^[\w\s,.?!]+\?\s*$', "Sure."),            # Matches questions
    ]
    for rule in rules:
        pattern, response = rule
        if re.match(pattern, what, re.UNICODE):
            return response

    return "Whatever."  # fallback return
