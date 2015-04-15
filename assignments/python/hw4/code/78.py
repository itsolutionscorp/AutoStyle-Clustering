def num_common_letters(goal_word, guess):
    list1 = get_list(goal_word)
    list2 = get_list(guess)
    j, y= 0, 0
    count = 0 
    while j < len(goal_word):
        i = 0 
        while i < len(goal_word):
            if list1[i] == list2[y]:
                count +=1 
            i +=1 
        y +=1
        j +=1 
    return count 
