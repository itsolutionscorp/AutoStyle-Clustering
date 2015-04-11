#!/usr/bin/env python3

def word_count(text):
    words = text.split()
    counts = {}
    for word in words:
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1
    return counts

if __name__ == '__main__':
    import sys
    print(word_count(sys.argv[1]))
