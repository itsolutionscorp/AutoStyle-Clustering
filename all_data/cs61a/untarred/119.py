def num_common_letters(goal_word, guess):
    common_letters = 0
    goalcount = 0
    if get_list(guess) == get_list(goal_word): #if list == list
        return len(get_list(guess))
    else:
        for item in get_list(guess): #going through every item in guess
            if get_list(goal_word)[goalcount] in get_list(guess):
                common_letters += 1
                #if letter 1 of goal inside guess
            goalcount += 1 
            #start next letter of goal to search in guess while loop
        return common_letters
