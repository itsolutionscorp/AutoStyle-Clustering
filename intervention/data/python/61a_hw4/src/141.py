def num_common_letters(goal_word, guess):
    hits = []
    for letter in guess:
        if letter in goal_word and letter not in hits:
            hits += [letter]
    return len(hits)
