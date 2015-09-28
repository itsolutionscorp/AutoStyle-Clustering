def num_common_letters(goal_word, guess):
    total = 0
    goalword = get_string (goal_word)
    guessword = get_string (guess)
    for elem1 in goalword:
        for elem2 in guessword:
            if elem1 == elem2:
                total += 1
                break
    return total
