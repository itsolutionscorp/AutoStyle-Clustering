import re
def word_count(sentence):
    word_counter = {}
    fixed_whitespace = re.sub(r'\s{3,}', ' ', sentence)
    for word in fixed_whitespace.replace('\n', ' ').split(' '):
        if word in word_counter:
            word_counter[word] += 1
        else:
            word_counter[word] = 1
    return word_counter
