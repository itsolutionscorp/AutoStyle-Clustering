#!/usr/bin/python

import sys

if __name__ == '__main__':
    out_file = sys.argv[1]
    og_words = ['cat', 'act', 'nom', 'omn']
    words = []
    for i in range(25000):
        words += og_words[:]
    with open(out_file, "w") as f:
        for word in words:
            f.write(word)
            f.write("\n")
