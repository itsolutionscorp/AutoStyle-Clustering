def word_count(sentence):
    result = dict()

    def hit_cleaning(word):
        import re
        no_chars = '[^a-zA-Z0-9]'

        # remove all non alphabetic or number characters
        word = re.sub(no_chars, '', word)
        # treat upper and lower case the same
        return word.lower()

    words = [hit_cleaning(word) for word in sentence.split()]

    for word in set(words):
        # ignore empty strings
        if word:
            result[word] = words.count(word)

    return result
