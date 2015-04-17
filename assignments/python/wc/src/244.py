def word_count(phrase):
    results = {}
    for word in phrase.split():
        if word in results:
            results[word] += 1
        else:
            results[word] = 1
    return results
