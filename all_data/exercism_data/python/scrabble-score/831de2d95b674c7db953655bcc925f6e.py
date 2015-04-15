score_dict = {'a': 1,
              'b': 3,
              'c': 3,
              'd': 2,
              'e': 1,
              'f': 4,
              'g': 2,
              'h': 4,
              'i': 1,
              'j': 8,
              'k': 5,
              'l': 1,
              'm': 3,
              'n': 1,
              'o': 1,
              'p': 3,
              'q': 10,
              'r': 1,
              's': 1,
              't': 1,
              'u': 1,
              'v': 4,
              'w': 4,
              'x': 8,
              'y': 4,
              'z': 10}
 # Correct input for double or triple letter score is to add *3 or *2 after the relevant letter
 # For word score multiplies, enter a second parameter to score(), 2 for double and 3 for triple.
def score(word, word_bonus = 1):
    word = word.lower() + ' ' # adds space to avoid a index out of range error on condition later
    score = 0
    for i in range(len(word)):
        if word[i].isalpha():
            if not word[i+1] == '*':
                score += score_dict[word[i]]
            elif word[i+2] == '2':
                score += score_dict[word[i]]*2
                i += 3
            elif word[i+2] == '3':
                score += score_dict[word[i]]*3
                i += 3

    return score * word_bonus
