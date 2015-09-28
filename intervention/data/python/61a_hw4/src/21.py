def num_common_letters(goal_word, guess):
    num = 0
    guess_list = get_list(guess)
    goal_list = get_list(goal_word)
    for i in goal_list:
        for j in guess_list:
            if i == j:
                num += 1
                break
    return num
