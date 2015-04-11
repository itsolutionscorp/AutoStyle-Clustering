from collections import Counter

def word_count(phrase):
    count = Counter(phrase.split())
    return count

# Non Counter version
# def word_count(phrase):
#     count = {}

#     for word in phrase.split():
#         word = word.strip()
#         count[word] = count.setdefault(word, 0) + 1

#     return count
