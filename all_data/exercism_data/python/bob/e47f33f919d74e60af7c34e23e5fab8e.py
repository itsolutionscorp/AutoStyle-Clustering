#!/usr/bin/python
# -*- coding: utf-8 -*-


class statement_analysis():
    """Provide properties of a given statement as @property functions
    """

    def __init__(self, statement):
        self.text = statement

    @property
    def is_shouting(self):
        return self.text.isupper()

    @property
    def is_question(self):
        return self.text.endswith('?')

    @property
    def is_empty(self):
        if(not self.text):
            return True
        else:
            return False

    @property
    def has_tab(self):
        return (self.text.find('\t') != -1)


def hey(statement):
    analysis = statement_analysis(statement)

    # now check for the specific conditions
    if((analysis.is_question and not analysis.is_shouting)):
        return "Sure."

    if(analysis.is_empty or analysis.has_tab):
        return 'Fine. Be that way!'

    if(analysis.is_shouting):
        return 'Whoa, chill out!'

    return 'Whatever.'
