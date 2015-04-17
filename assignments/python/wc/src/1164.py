from collections import Counter
import re
def word_count(phrase):
    word_count = {}
    phrase = str(phrase)
    if not phrase:
        return word_count
    phrase = re.sub(' +', ' ', phrase)
    word_list = re.split(' |\n', phrase)
    return Counter(word_list)
