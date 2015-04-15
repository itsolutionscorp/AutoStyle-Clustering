def num_common_letters(goal_word, guess):
    goal_w = get_list(goal_word)
    w = get_list(guess)
    counter = []
    for i in goal_w:
        for k in w: #can repeat values 
            if k not in counter:  
                if i == k:
                    counter += [k] 
    return len(counter) 
