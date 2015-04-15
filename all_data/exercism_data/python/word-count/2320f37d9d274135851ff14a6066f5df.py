def word_count(input):
    count_map = {}
    for line in input.split("\n"):
        for word in line.strip().split():
           count_map[word] = count_map.get(word, 0)  + 1
    return count_map
