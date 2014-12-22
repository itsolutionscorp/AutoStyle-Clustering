def num_common_letters(goal_word, guess):
    guesslst = get_list(guess)
    goallst = get_list(goal_word)
    count = 0
    for elem in goallst:
        if guesslst.count(elem) >= 1:
            count += 1
    return count
        
