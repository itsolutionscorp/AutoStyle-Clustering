def word_count(input_string):
    results = {}

    for each_word in input_string.lower().split():
        words = ''.join(e for e in each_word if e.isalnum())
        if words:
            results[words] = results.get(words, 0) + 1

    return results
