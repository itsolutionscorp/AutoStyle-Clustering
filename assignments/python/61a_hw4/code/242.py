def num_common_letters(goal_word, guess):
    total = 0
    for guess_char in get_string(goal_word):
        for goal_char in get_string(guess):
            if guess_char == goal_char:
                total += 1
                break
    return total
