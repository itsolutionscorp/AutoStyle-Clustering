#!/usr/bin/env python
# -*- coding: utf-8 -*-

# from sys import exit
from string import maketrans


def to_rna(text):
    m = maketrans('ATGC', 'UACG')
    return text.translate(m)

# def to_rna(text):
#     res = ''
#     if type(text) is not str:
#         print "Please type text."
#         exit(0)
#     ext = [x for x in text if x not in ['A', 'T', 'G', 'C']]
#     if len(ext) > 0:
#         print "Wrong input."
#         exit(0)
#     for ch in text:
#         if ch == 'A':
#             res += 'U'
#         elif ch == 'T':
#             res += 'A'
#         elif ch == 'C':
#             res += 'G'
#         elif ch == 'G':
#             res += 'C'
#     return res
