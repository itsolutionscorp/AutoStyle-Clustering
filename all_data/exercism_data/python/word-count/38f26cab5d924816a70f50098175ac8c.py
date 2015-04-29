from string import punctuation
from collections import defaultdict

def word_count(string):
    # Remove punctuation
    string = ''.join(ch for ch in string if ch not in punctuation)
    words = string.split(' ')
    # Change to lowercase
    words = [word.lower() for word in words if len(word) > 0]
    count = defaultdict(int)
    for word in words:
        count[word] += 1

    return count
