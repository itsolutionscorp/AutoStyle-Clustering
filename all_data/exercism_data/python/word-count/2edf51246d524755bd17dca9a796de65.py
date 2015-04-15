def word_count(str):

    result = {}
    listOfWords = str.split()
    unique_words = set(listOfWords)
    for word in unique_words:
        result[word] = listOfWords.count(word)

    return result
