from collections import Counter
import re
def word_count(inputString):
    return Counter(re.split('\s+', inputString))
