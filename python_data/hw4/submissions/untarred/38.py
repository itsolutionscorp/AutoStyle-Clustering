def num_common_letters(goal_word, guess):
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    common_letter = 0
    x = 0
    goal_len = len(goal_lst)
    guess_len = len(guess_lst)
    while x < goal_len:
        y = 0
        while y < guess_len:
            if goal_lst[x] == guess_lst[y]:
                common_letter += 1
                y = guess_len + 1
            else:
                y += 1
        x += 1
    return common_letter
