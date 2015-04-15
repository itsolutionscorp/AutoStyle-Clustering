import re
match_words = re.compile(r'\b\w+\b')

def word_count(input):
    result = {}
    words = [w.lower() for w in match_words.findall(input)]
    for w in words:
        if w in result:
            result[w] += 1
        else:
            result[w] = 1
    return result
