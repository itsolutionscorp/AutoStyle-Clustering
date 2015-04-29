#!/usr/bin/env python
# -*- coding: utf-8 -*-


class DNA(object):
    d_dict = {'T': 'U', 'G': 'G', 'C': 'C'}

    def __init__(self, input_):
        self._input = input_

    def to_rna(self):
        return ''.join([self.d_dict.get(x, x) for x in self._input])
