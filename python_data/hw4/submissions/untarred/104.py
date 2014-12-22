def num_common_letters(goal_word, guess):
    doubles = 0
    repeat = 0
    common = 0
    user_guess = get_list(guess)
    goal_list = get_list(goal_word)
    user_guess_string = get_string(guess)
    for letter in goal_list:
        for char in user_guess:
            if letter in char:
                doubles +=1
                if doubles > 1:
                    repeat +=1
                    doubles = 0
        doubles = 0
        if letter in user_guess_string:
            common+=1
    return common 
