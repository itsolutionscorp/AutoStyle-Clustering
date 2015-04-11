#!/bin/python3
"""
exercism/python/bob/bob.py

'A limited conversation bot'
"""


def hey(statement):
    """Bob responds to sentances depending on their final character."""
    if len(statement.strip()) == 0:
        return 'Fine. Be that way!'
    if statement.upper() == statement and statement.lower() != statement:
        return "Woah, chill out!"
    if statement[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
