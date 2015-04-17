def word_count(phrase):
    words = {}   # Create empty dictinary to store words and number off occurences
    phrase_list = phrase.split()
    for word in phrase_list:       #Begin iterating through words and comparing to others
        if words.has_key(word) == False:
            words[word] = phrase_list.count(word)
    return words
