#!/usr/bin/env python
# -*- coding: utf-8 -*-
# FILE: dna.py
# AUTHOR: haya14busa
# License: MIT license
#
#     Permission is hereby granted, free of charge, to any person obtaining
#     a copy of this software and associated documentation files (the
#     "Software"), to deal in the Software without restriction, including
#     without limitation the rights to use, copy, modify, merge, publish,
#     distribute, sublicense, and/or sell copies of the Software, and to
#     permit persons to whom the Software is furnished to do so, subject to
#     the following conditions:
#
#     The above copyright notice and this permission notice shall be included
#     in all copies or substantial portions of the Software.
#
#     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
#     OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
#     MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
#     IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
#     CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
#     TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
#     SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#
#=============================================================================


import string

'''
maketrans(...)
    maketrans(frm, to) -> string

    Return a translation table (a string of 256 bytes long)
    suitable for use in string.translate.  The strings frm and to
    must be of the same length.
'''

class DNA(object):
    # Translation table(constant)
    DNA_TO_RNA_TABLE = string.maketrans('GCTA',
                                        'CGAU')

    def __init__(self, dna_strand):
        """
        :param dna_strand:
            The four nucleotides found in DNA are
                1. adenine   (**A**)
                2. cytosine  (**C**)
                3. guanine   (**G**)
                4. thymidine (**T**).
        """
        # TODO: confirm given dna strand is the sequence of A,C,G,T
        self.dna = dna_strand

    def to_rna(self):
        """to_rna
            DNA -> RNA
            `G` -> `C`
            `C` -> `G`
            `T` -> `A`
            `A` -> `U`
        """
        return self.dna.translate(DNA.DNA_TO_RNA_TABLE)
