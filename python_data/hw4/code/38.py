def num_common_letters(goal_word, guess):
    goal_word_list = get_list(goal_word)
    guess_list = get_list(guess)
    a = 0
    for el in goal_word_list:
        if el in guess_list:
            a+=1
    return a
