from collections import Counter
import re
def word_count(s):
    split = re.split(r' |\n', s)
    count = Counter(split)
    del count['']
    return count
