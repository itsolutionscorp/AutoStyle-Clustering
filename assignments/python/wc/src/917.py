import re
from collections import Counter
'''
Find and count all words in the provided phrase.
Gah, tests changed drastically since my last submission.
Changed from a Phrase class with a word_count(word) method to
just a word_count(phrase) function, and it looks like 'word'
is just 'non-whitespace' now.
Regex + collections.Counter makes this kinda trivial. Oh well.
'''
_word_regex = re.compile(r"\S+")
def word_count(phrase):
    words = _word_regex.findall(phrase)
    return Counter(words)
