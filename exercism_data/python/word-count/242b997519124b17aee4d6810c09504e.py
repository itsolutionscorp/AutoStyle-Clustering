import re
from collections import defaultdict

def word_count(words):
    result = defaultdict(int)
    for word in re.findall('\w+', words):
        result[word.lower()] += 1
    return result
    
