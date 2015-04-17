import re
from collections import Counter
def word_count(phrase):
    word_list = re.findall('\w+', phrase.lower())
    return dict(Counter(word_list))
