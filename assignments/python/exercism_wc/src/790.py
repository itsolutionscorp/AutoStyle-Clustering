def word_count(phrase):
    phrase = phrase.replace('\n', ' ').replace('\t', ' ')
    words = phrase.split(' ')
    results = {}
    for word in words:
        if word:
            if not results.get(word):
                results[word] = 1
            else:
                results[word] = results[word] + 1
    return results
