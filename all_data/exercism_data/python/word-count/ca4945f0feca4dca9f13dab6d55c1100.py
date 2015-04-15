from string import punctuation
def word_count(msg):
    exclude = set(punctuation)
    msg = ''.join(ch for ch in msg if ch not in exclude).lower()
    d = {}
    for word in msg.split():
        # hack to fix key error
        if word in d:
            d[word] += 1
        else:
            d[word] = 1
    return d

print(word_count("The the fox fox"))
