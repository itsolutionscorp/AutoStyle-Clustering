#!/usr/bin/env python
# encoding: utf-8

'''
from README.md:
# Rna Transcription

Write a program that, given a DNA strand,
returns its RNA complement (per RNA transcription).

Both DNA and RNA strands are a sequence of nucleotides.

The four nucleotides found in DNA are adenine (**A**), cytosine (**C**),
guanine (**G**) and thymidine (**T**).

The four nucleotides found in RNA are adenine (**A**), cytosine (**C**),
guanine (**G**) and uracil (**U**).

Given a DNA strand, its transcribed RNA strand is formed by replacing
each nucleotide with its complement:

* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`

## Source

Rosalind [view source](http://rosalind.info/problems/rna)
'''

import os
import sys
import argparse
import pdb


def to_rna(dna_strand):
    dna = DNA()
    return dna.to_rna(dna_strand)


class DNA:
    def __init__(self):
        self.dna_to_rna = {
            'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U',
        }

    def to_rna(self, dna_strand):
        rna = ''
        for item in dna_strand:
            if item in self.dna_to_rna:
                rna += self.dna_to_rna[item]
        if len(rna) == 0:
            rna = None
        return rna

if __name__ == '__main__':
    program_name = os.path.basename(sys.argv[0])
    program_license = '''%s
The MIT License (MIT)

Copyright (c) 2014 Alan Schmitz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
USAGE
''' % (program_name)

    try:
        parser = argparse.ArgumentParser(
            description=program_license,
            formatter_class=argparse.RawDescriptionHelpFormatter)
        parser.add_argument(
            '-r', '--rna',
            action='store',
            dest='dna',
            default='G',
            help='convert dna to rna')
        my_args = parser.parse_args()
    except Exception as e:
        raise(e)
        indent = len(program_name) * ' '
        sys.stderr.write(program_name + ': ' + repr(e) + '\n')
        sys.stderr.write(indent + ' for help use --help')
        sys.exit(-1)

    dna = DNA()
    print('DNA: ' + my_args.dna + ' -> ' + dna.to_rna(my_args.dna))

# vi:ts=4:sw=4:expandtab:ft=python:
