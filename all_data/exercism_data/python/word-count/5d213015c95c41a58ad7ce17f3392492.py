from collections import Counter
import re

def word_count(sentence):
    return Counter(re.sub(r"[^\w ]+", '', sentence.lower()).split())
