def word_count(line):
    table = dict()
    tokens = line.split();
    for word in tokens:
        word = word.strip()
        if table.has_key(word):
            count = table.get(word)
            count = count + 1
            table[word] = count
        else:
            table[word] = 1
    return table
