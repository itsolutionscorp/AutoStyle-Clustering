def word_count(inp):
    words = {}
    for input in inp.split():
        if input:
            words[input] = words.get(input, 0) + 1
    return words
