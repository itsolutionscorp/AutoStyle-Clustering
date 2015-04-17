import re
from collections import Counter
def word_count(sentence):
    sentence = sentence.lower()
    sentence = re.sub('[^a-z\d\s]', '', sentence)
    word_list = sentence.split()
    count_dict = Counter(word_list)
    return count_dict
