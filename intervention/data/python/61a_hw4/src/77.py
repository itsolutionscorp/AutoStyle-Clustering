def num_common_letters(goal_word, guess):
    total = 0
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    singles = []
    if len(goal_lst) == 0:
        return 0
    else:
        for elem in guess_lst:
            if elem not in singles:
                singles += [elem]
        for elem in singles:
            if elem is goal_lst[0]:
                total += 1
    return total + num_common_letters(make_word_from_list(goal_lst[1:]), guess)
