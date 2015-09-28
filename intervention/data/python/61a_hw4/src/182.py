def num_common_letters(goal_word, guess):
    index=0
    num_correct=0
    while index<len(goal_word):
        index_inner=0
        num_correct_inner=0
        while index_inner<len(guess):
            if (list(goal_word)[index])==(list(guess)[index_inner]):
                num_correct_inner+=1
            index_inner+=1
        if num_correct_inner>0:
            num_correct+=1
        index+=1
    return num_correct
