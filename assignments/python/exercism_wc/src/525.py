def word_count(phrase):
    phrase = ''.join([ch for ch in phrase if ch.isalnum() or ch.isspace()])
    count = {}
    for word in phrase.lower().split():
        if not word in count:
            count[word] = 0
        count[word] += 1
    return count
