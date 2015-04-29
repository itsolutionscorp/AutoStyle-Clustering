#!/usr/bin/python
# -*- coding: utf-8 -*-


def hey(statement):
    is_shouting = statement.isupper()
    is_question = statement.strip().endswith('?')
    is_empty = (statement.strip() == "")

    if is_shouting:
        return 'Whoa, chill out!'

    if is_question:
        return "Sure."

    if is_empty:
        return 'Fine. Be that way!'

    return 'Whatever.'
