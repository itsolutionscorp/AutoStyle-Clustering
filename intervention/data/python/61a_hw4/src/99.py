def num_common_letters(goal_word, guess):
    num = 0
    tally = "" 
    guess_list = get_list(guess)
    goal_list = get_list(goal_word)
    for i in guess_list:
        if i in goal_list and i not in tally:
            num += 1
            tally = tally + i
    return num
