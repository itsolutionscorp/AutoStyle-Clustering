from collections import defaultdict
def word_count(phrase):
    counter = defaultdict(lambda: 0)
    for word in phrase.split(' '):
        normalized_word = ''
        for char in word:
            if char.isalnum():
                normalized_word += char
        if normalized_word:
            counter[normalized_word.lower()]+=1
    return counter
