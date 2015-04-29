__author__ = 'Cedric Zhuang'


def detect_anagrams(sample, words):
    sample_upper = sample.upper()
    word_byte_array = normalize(sample_upper)
    anagrams = []
    for word in words:
        word_upper = word.upper()
        if word_upper == sample_upper:
            # same word is not anagram
            continue
        elif word_byte_array == normalize(word_upper):
            anagrams.append(word)
    return anagrams


def normalize(word):
    return sorted(bytearray(word))
