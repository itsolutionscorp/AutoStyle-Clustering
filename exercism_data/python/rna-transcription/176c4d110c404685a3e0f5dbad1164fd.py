#! /usr/bin/env python


class DNA:

    chain = ""

    def __init__(self, str=''):
        self.chain = str

    def to_rna(self):
        return self.chain.replace('T', 'U')