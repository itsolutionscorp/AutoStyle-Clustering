import re
def word_count(string):
    word_list = re.findall(r"\w+",string.lower())
    words = {}
    for item in word_list:
        if not item in words:
            words[item] = word_list.count(item)
    return words
