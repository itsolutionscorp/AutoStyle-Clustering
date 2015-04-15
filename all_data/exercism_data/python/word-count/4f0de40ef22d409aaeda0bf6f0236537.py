def word_count(values):
    count={}
    for w in values.split():
        word = w.lower().strip(':!@#$%^&*(),.;?/+-')
        if word:
            if word in count:
                count[word] += 1
            else:
                count[word] = 1
    return count
