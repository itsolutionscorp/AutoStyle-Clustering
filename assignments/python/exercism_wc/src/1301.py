import re
def word_count(string):
    lower = string.lower()
    regex = re.compile("[a-z0-9]+")
    words = regex.findall(lower)
    dict = {}
    for word in words:
        if word in dict:
            dict[word] += 1
        else:
            dict[word] = 1
    return dict
