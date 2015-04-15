def num_common_letters(goal_word, guess):
    lst1 = get_list(goal_word)
    lst2 = get_list(guess)
    count = 0
    result_common = []
    for i in lst1:
        for c in lst2:
            if c == i and c not in result_common:
                result_common += [c]
                count +=1
        
    return count
