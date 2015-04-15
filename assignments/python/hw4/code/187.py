def num_common_letters(goal_word, guess):
    goal_word_list = get_list(goal_word);
    guess_word_list = get_list(guess)
    if goal_word_list == []:
        return 0
    for guess_letter in guess_word_list:
        if guess_letter == goal_word_list[0]:
            return 1 + num_common_letters(goal_word[1:], guess)
    return 0 + num_common_letters(goal_word[1:], guess)
