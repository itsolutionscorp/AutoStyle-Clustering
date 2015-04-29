import re
def word_count(phrase):
    words_occcurrences = dict()
    phrase_words = re.split('[ \n\t]+', phrase)
    for word in phrase_words:
        words_occcurrences[word] = words_occcurrences.get(word, 0) + 1
    return words_occcurrences
