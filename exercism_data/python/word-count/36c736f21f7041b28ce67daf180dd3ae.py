# coding=utf-8

def word_count(phrase):
    num_words = {}

    # split phrase by space into words list
    for word in phrase.strip().split():
        # increase occurence of 
        # particular word by 1
        num_words[word] = num_words.setdefault(word, 0) + 1

    return num_words
