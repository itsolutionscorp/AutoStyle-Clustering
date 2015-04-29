from typing import Iterable, List
def detect_anagrams(word, candidates):
    word_hash = sum(word.encode('ascii'))
    word_len = len(word)
    return list(
        filter(lambda c:
               c != word and
               len(c) == word_len and
               sum(c.encode('ascii')) == word_hash, candidates))
