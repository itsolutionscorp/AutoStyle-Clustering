def word_count(line):
    count = {}
    word_list = line.strip().split()
    for word in word_list:
        count[word] = count.get(word, 0) + 1
    return count
