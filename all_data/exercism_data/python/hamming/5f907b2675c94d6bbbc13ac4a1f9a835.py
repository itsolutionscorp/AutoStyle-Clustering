from itertools import compress, imap


def distance(strand1, strand2):
    # imap returns an iterator containing 0 only if the nucleotides were the
    # same. compress then discards nucleotides that were the same in both
    # strands from strand1. We then take the length of the resulting iterator
    # to know the Hamming difference.
    return len(list(compress(strand1, imap(lambda n1, n2: ord(n1) - ord(n2), strand1, strand2))))
