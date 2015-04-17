from collections import Counter
def word_count(line):
    counter = Counter()
    for word in line.split():
        counter[word] += 1            
    return counter
