def word_count(phrase):
    word_list = phrase.split()
    results = dict.fromkeys(word_list, 0)
    for word in word_list:
        results[word] += 1
    return results


# EOF
