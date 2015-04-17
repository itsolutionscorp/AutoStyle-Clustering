def word_count(phrase):
    count = {}
    all_words = phrase.strip().split()
    unique_words = set(all_words)
    for word in unique_words:
        count[word] = all_words.count(word)
    return count
