import string
import re

table = string.maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba')
regex = '[^a-z0-9]+'

def decode(ct):
    
    return ct.translate(table, ' ')


def encode(pt):

    # shift to lowercase and remove non alphanumeric
    pt_clean = re.sub(regex, '', pt.lower())

    # ct without spacing
    ct_clumped = pt_clean.translate(table)

    # add spacing
    ct = ' '.join(ct_clumped[i:i+5] for i in xrange(0, len(ct_clumped), 5))

    return ct
