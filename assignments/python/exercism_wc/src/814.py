import re
def word_count(input):
    words = re.split(r"\s+", input)
    distinct_words = set(words)
    return {word: words.count(word) for word in distinct_words}
