import re
def word_count(str):
    str = re.sub('[^0-9a-z\s]+', ' ', str.lower())
    count = {}
    for word in str.split():
        if word in count:
            n = count[word] + 1
        else:
            n = 1
        count.update({ word: n })
    return count
