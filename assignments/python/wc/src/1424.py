def word_count(line):
    line = line.split()
    words = {} 
    for word in line:
        if word not in words:
            words[word] = line.count(word)
    return words
