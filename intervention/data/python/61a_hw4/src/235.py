def num_common_letters(goal_word, guess):
    guessed = []
    right = 0
    for elm in guess:
        if elm in goal_word and elm not in guessed:
            right += 1
            guessed += [elm]
    return right
