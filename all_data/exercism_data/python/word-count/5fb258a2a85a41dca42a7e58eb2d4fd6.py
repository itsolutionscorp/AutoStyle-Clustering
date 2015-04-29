def word_count(s):
    string = s.lower().split()
    print string
    for i in range(0, len(string)):
        string[i] = string[i].strip(',!@:#$%^&*()')
    d = {}
    for word in string:
        if len(word) > 0:
            d[word] = string.count(word)
    return d
