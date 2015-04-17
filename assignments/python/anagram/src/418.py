def detect_anagrams(word, possibilities):
    matches = []
    # Break the word up into a list of letters.
    word_letters = sorted(list(word.upper()))
    for possibility in possibilities:
        # If the word is the same as the possibility to check, skip to the next item.
        if word.upper() == possibility.upper():
            continue
        # If letters in the word and possibility match, add it to our return list.
        if word_letters == sorted(list(possibility.upper())):
            matches.append(possibility)
    return matches
