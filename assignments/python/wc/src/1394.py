def word_count(phrase):
    words = phrase.split()
    cnt = dict()
    for word in words:
        if word in cnt:
            cnt[word] += 1
        else:
            cnt[word] = 1
    return cnt
