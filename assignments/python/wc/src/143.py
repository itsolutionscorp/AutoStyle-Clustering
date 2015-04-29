import re
def word_count(sentence):
    word_count = {}
    for w in re.split('\W+', sentence):
        if w == "":
            continue
        else:
            w = w.lower()
        if w in word_count:
            word_count[w] += 1
        else:
            word_count[w] = 1
    return word_count
if __name__ == '__main__':
    import sys
    if len(sys.argv) != 2:
        print "Usage: ./wordcount.py '<sentence>'"
        sys.exit()
    print word_count(sys.argv[1])
