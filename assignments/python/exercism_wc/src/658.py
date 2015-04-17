def word_count(text):
    words = text.split()
    return {item: words.count(item) for item in set(words)}
if __name__ == '__main__':
    import sys
    print(word_count(sys.argv[1]))
