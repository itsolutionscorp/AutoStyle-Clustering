def num_common_letters(goal_word, guess):
    lst_goal, lst_guess = get_list(goal_word), get_list (guess)
    total, checked_letters = 0, []
    for letter in lst_guess:
        if letter in lst_goal and letter not in checked_letters:
            total += 1
        checked_letters += [letter]
    return total
    
