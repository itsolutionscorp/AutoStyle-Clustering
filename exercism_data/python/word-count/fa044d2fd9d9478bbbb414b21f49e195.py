def word_count(words):
    wordDB = {}
    for word in words.split():
        # remove non-alphanumeric characters and everything to lower case
        word = ''.join(ch for ch in word if ch.isalnum()).lower()
        if not word:
            continue
        if word in wordDB:
            wordDB[word] += 1
        else:
            wordDB[word] = 1
    return wordDB
