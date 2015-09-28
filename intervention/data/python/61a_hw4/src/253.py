def num_common_letters(goal_word, guess):
    total = 0
    for i in get_string(goal_word):
        for j in get_string(guess):
            if i == j:
                total += 1
                break
    return total
