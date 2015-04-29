def num_common_letters(goal_word, guess):
    list1=get_list(goal_word)
    list2=get_list(guess)
    count=0
    list2_no_repeat=[]
    list2_no_repeat=list(set(list2))
            
    for x in range(len(list1)):
        for y in range(len(list2_no_repeat)):
            if list1[x]==list2_no_repeat[y]:
                count+=1
    return count 
