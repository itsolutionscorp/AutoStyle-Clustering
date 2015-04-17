from collections import Counter
def word_count(input_words):
    words = input_words.split(u' ')
    counter = Counter()
    for word in words:
        counter[word] += 1
