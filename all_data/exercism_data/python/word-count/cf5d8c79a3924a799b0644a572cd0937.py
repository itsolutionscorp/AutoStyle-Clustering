from collections import Counter
import re
def word_count(input):
    pattern = r"\w+"
    words = re.findall(pattern, input)
    return Counter([word.lower() for word in words])
