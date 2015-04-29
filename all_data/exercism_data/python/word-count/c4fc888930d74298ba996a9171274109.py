from collections import defaultdict

def word_count(phrase):
    array_phrase = phrase.replace('\n'," ").replace('\t'," ").split(" ")
    wcount = defaultdict(int)
    for element in array_phrase:
        if len(element) != 0 :
            wcount[element] += 1
    return wcount
