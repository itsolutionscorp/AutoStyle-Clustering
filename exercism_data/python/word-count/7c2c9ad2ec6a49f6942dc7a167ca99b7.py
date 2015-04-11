from collections import Counter
import re

def word_count(text):
    return Counter( [word for word in re.split("\s", text) if word] )
