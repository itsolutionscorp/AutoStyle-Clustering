import re
def word_count(phrase):
    words = phrase.lower().split()
    words = [re.search('[a-z0-9]+', a).group(0) for a in words if re.search('[a-z0-9]', a)]
    unique_words = list(set(words))
    output = {}
    for word in unique_words:
        matches = [a for a in words if a == word]
        output[word] = len(matches)
    return output
