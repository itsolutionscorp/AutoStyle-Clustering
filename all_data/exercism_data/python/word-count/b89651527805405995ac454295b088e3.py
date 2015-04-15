def word_count(input_str):
    results = {}
    words = input_str.split()

    for word in words:
        if word in results:
            results[word] += 1
        else:
            results[word] = 1

    return results
