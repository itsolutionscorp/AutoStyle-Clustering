import re
from collections import Counter
def word_count(sentence):
    return Counter(re.sub('[^a-z\d\s]', '', sentence.lower()).split())
