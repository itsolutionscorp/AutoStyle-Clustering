import collections
import re
def word_count(text):
    counter = collections.Counter()
    for word in re.findall(r"\S+", text):
      counter[word] += 1
    return counter
