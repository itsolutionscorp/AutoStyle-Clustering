""" Returns counts of each word in given string s
"""
def word_count(s):
    counts = {}
    words = ''.join(c for c in s if c.isalnum() or c.isspace()).split()
    for w in words:
        temp = w.lower()
        if temp not in counts:
            counts[temp] = 1
        else:
            counts[temp] += 1
    return counts
