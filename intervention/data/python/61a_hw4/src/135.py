def num_common_letters(goal_word, guess):
    goal_word_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    guess_lst = list(set(guess_lst))
    in_common = 0
    for elem1 in goal_word_lst:
        for elem2 in guess_lst:
            if elem1 == elem2:
                in_common += 1
    return in_common
