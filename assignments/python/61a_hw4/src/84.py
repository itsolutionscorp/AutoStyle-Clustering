def num_common_letters(goal_word, guess):
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    common_letters = []
    for letter in goal_lst:
        if letter in guess_lst:
            common_letters += [letter]
    return len(common_letters)
