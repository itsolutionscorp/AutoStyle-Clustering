import re
from collections import Counter


# Horribly short way to do this, previous version are more commented!
def word_count(sentence):
    return Counter(re.sub('[^a-z\d\s]', '', sentence.lower()).split())
