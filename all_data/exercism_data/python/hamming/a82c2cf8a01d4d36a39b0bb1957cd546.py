#!/usr/bin/env python3

def distance(from_strand, to_strand):
    return sum((from_nucleotide != to_nucleotide)
            for from_nucleotide, to_nucleotide  in zip(from_strand, to_strand))
