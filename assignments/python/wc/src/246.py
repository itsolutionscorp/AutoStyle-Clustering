def word_count(s):
    words = {}
    for line in s.splitlines():
        for w in line.split(" "):
            if w == "":
                continue
            if w not in words:
                words[w] = 1
            else:
                words[w] += 1
    return words
'''
Used to test function from cmd line
'''
if __name__ == "__main__":
    s = "This is a test."
    print word_count(s)
