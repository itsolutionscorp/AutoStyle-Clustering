def num_common_letters(goal_word, guess):
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    count = 0
    for i in goal_list:
        if i in guess_list:
            count += 1
    return count
