def num_common_letters(goal_word, guess):
    goal_word_list=get_list(goal_word)
    guess_word_list=get_list(guess)
    total=0
    for elem in goal_word_list:
        if elem in guess_word_list:
            total+=1
    return total
