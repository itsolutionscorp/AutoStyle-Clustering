from itertools import imap
import operator

def distance(seq1, seq2):
    not_equals = operator.ne
    return sum(imap(not_equals, seq1, seq2))
