def num_common_letters(goal_word, guess):
    num_common = 0
    counter_goal = 0
    counter_guess = 0
    counter = 0
    counter2 = 0
    switch = 0
    guess_list = get_list(guess)
    nodupe_list = []
    guess_length = len(guess_list)
    goal_list = get_list(goal_word)
    goal_length = len(get_list(goal_word))
    while counter < guess_length:
        counter2 = counter+1
        while counter2 < guess_length:
            if guess_list[counter] == guess_list[counter2]:
                switch+=1
            counter2+= 1
        if switch > 0:
            switch = 0
        else:
            nodupe_list = nodupe_list + [guess_list[counter]]
            switch = 0
        counter+= 1
    nodupe_guess = make_word_from_list(nodupe_list)
    while counter_goal < goal_length:
        while counter_guess < len(nodupe_list):
            if goal_list[counter_goal] == nodupe_list[counter_guess]:
                num_common+= 1
            counter_guess+= 1
        counter_guess = 0
        counter_goal += 1
    return num_common
    
