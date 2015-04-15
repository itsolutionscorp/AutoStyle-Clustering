def num_common_letters(goal_word, guess):
    mwfs, mwfl = make_word_from_string, make_word_from_list
    common_letters = []
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    while goal_list != [] and guess_list != []:
        if guess_list[0] in goal_list:
            if guess_list[0] in common_letters:
                guess_list = guess_list[1:]
            else:
                common_letters += [guess_list[0]]
                guess_list = guess_list[1:]
        else:
            guess_list = guess_list[1:]
    return len(common_letters)
