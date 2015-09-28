def num_common_letters(goal_word, guess):
    goal=get_list(goal_word)
    g=get_list(guess)
    x=len(goal)
    match=0
    index=0
    while (x>0):
        if (index==len(g)):
            return match
        if (goal[index] in g):
            match+=1
                    
        index+=1
        x-=1
    return match
