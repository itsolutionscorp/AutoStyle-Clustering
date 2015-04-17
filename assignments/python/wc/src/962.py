import re
def word_count(sentence):
    words = re.split(r"\s|\n", sentence) if sentence else []
    count = {}
    for word in words:
        if word:
            count[word] = count[word] + 1 if word in count else 1
    return count
