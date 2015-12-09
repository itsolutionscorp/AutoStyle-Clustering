def num_common_letters(goal_word, guess):
    used = []
    for letter in goal_word:
        if letter not in used and letter in guess:
            used.append(letter)
    return len(used)
