def num_common_letters(goal_word, guess):
    goal_list=get_list(goal_word)
    guess_list= get_list(guess)
    commons= [item for item in goal_list if item in guess_list]
    return len(commons)
