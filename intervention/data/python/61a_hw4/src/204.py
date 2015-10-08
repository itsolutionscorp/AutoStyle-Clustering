def num_common_letters(goal_word, guess):
    goal_word, guess=get_list(goal_word), get_list(guess) #make both of type list
    clean_guess=list(set(guess)) #get rid of repeats
    num_in_common=0
    for i in range(len(goal_word)):
        temp_letter=goal_word[i]
        for j in range(len(clean_guess)):
            if temp_letter==clean_guess[j]:
                num_in_common+=1
    return num_in_common
