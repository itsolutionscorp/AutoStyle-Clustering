def num_common_letters(goal_word, guess):
    count=0
    emp=[]
    for i in get_list(guess):
        if i in get_list(goal_word) and i not in emp:
            count+=1
            emp+=i
    return count
