#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import string

def to_rna(char):
    t = string.maketrans('GCTA', 'CGAU')
    return char.translate(t)
