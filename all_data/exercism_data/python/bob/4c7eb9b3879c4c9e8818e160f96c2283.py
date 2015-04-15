#!/usr/bin/python
# -*- coding: utf-8 -*-


def hey(statement):
    # analyse the statement
    is_shouting = statement.isupper()
    is_question = statement.strip().endswith('?')
    is_empty = (statement.strip() == "")

    # now check for the specific conditions
    if((is_question and not is_shouting)):
        return "Sure."

    if(is_empty):
        return 'Fine. Be that way!'

    if(is_shouting):
        return 'Whoa, chill out!'

    return 'Whatever.'
