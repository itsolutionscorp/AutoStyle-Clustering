def num_common_letters(goal_word, guess):
    goal_list = get_list(goal_word)
    guess_list = set(get_list(guess))
    common_letters = 0
    for x in goal_list:
        for y in guess_list:
            if x==y:
                common_letters += 1
    return common_letters
