import re
def word_count(input_s):
    counts = {}   
    words = re.sub("[^0-9a-zA-Z ]", '', input_s).lower().split()
    for w in words:
        counts[w] = counts.get(w, 0) + 1
    return counts
