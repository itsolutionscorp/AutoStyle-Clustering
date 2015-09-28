def num_common_letters(goal_word, guess):
    lst=[]
    count=0
    for i in goal_word:
        for j in guess:
            if i==j:
                count+=1
                lst.append(j)
                if lst.count(j)>1:
                    count-=1
    return count
