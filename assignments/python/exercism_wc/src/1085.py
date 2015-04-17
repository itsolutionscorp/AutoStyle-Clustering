import re
def word_count(text):
    wordcount = {}
    for word in re.findall(r"\w[\w']*", text.lower()):
        if word in wordcount:
            wordcount[word] += 1
        else:
            wordcount[word] = 1
    return wordcount
