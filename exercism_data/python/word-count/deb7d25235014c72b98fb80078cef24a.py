def word_count1(phrase):
    # Classical hashtable. get method with a
    # default value saves lines of code
    words = {}
    for w in phrase.split():
        words[w] = words.get(w, 0) + 1
    return words

def word_count2(phrase):
    # Nothing to do if we use a Counter !
    from collections import Counter
    return dict(Counter(phrase.split()))

word_count = word_count2
