def num_common_letters(goal_word, guess):
    repeat=[]
    count=0
    for item in get_list(goal_word):
        if item in get_list(guess) and item not in repeat:
            repeat.append(item)
            count+=1
    return count 
