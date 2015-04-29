#!/usr/bin/env python

class DNA:
    in_string = ''
    def __init__(self, in_string):
        self.in_string = in_string

    def to_rna(self):
        return self.in_string.replace('T', 'U')
