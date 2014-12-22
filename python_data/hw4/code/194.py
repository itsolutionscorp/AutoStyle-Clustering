def num_common_letters(goal_word, guess):
    goal_lst=get_list(goal_word)
    guess_lst=get_list(guess)
    num=0
    for k in range(len(guess_lst)):
        for l in range(k): 
            if guess_lst[l]==guess_lst[k]:
                guess_lst[k]=[]
    for i in range(len(goal_lst)):
        for j in range(len(guess_lst)):
            if goal_lst[i]==guess_lst[j]:
                num+=1
    return num
