from collections import Counter
def word_count(words):
    wordCount = Counter()
    for word in words.split():
        word = ''.join(ch for ch in word if ch.isalnum()).lower()
        if not word:
            continue
        wordCount[word] += 1
    return wordCount
