def word_count(words):
    freq = dict()
    words = ''.join(filter(lambda c : c == ' ' or c.isalnum(), words));
    words = [str(w).lower() for w in words.split(' ') if str(w).isalnum()]
    for word in words:
        freq[word] = freq.get(word, 0) + 1
    return freq
