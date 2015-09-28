def num_common_letters(goal_word, guess):
    lst_goal_word= get_list(goal_word)
    lst_guess=get_list(guess)
    string_goal_word=get_string(goal_word)
    string_guess= get_string(guess)
    total=0
    for letter in lst_goal_word:
        if len(lst_goal_word)==len(lst_guess):
            total+=num_common_letters()
    return total
    total=0 
    for letter in string_goal_word:
        if len(string_goal_word)==len(string_guess):
            total+=num_common_letters()
    return total 
