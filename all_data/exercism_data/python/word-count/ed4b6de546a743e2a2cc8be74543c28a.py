from collections import Counter
import re

onlyAlphaNumPattern = re.compile('[^\w\d\s]')

def word_count(textString):
    phrase = onlyAlphaNumPattern.sub('',textString)
    lowerCasePhrase = phrase.lower()
    wordlist = lowerCasePhrase.split()
    return Counter(wordlist)
