def num_common_letters(goal_word, guess):
    score = 0
    for el in get_list(goal_word):
        if el in get_list(guess):
            score += 1
    return score
