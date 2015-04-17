def num_common_letters(goal_word, guess):
    goal_word_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    count = 0
    for elem1 in goal_word_lst:
        for elem2 in guess_lst:
            if elem1 == elem2:
                count += 1
                break
    return count
