def num_common_letters(goal_word, guess):
    count=0
    for i in get_string(goal_word):
        if i in get_string(guess):
            count+=1   
    return count        
             
