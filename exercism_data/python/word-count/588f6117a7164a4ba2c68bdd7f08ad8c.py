def word_count(phrase):
    frequency_log = {}
    phrase = phrase.split()
    for word in phrase:
        if word in frequency_log.keys():
            frequency_log[word] += 1
        else:
            frequency_log[word] = 1
    return frequency_log
