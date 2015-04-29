import re
from collections import Counter
def word_count(a_string):
    a_string = a_string.lower()
    words = re.findall('\b*([^\W+]+)\b*', a_string)
    return Counter(words)
