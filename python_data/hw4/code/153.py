def num_common_letters(goal_word, guess):
    same_letter=0
    lst1=get_list(goal_word)
    lst2=get_list(guess)
    for letter1 in lst1:
        for letter2 in lst2:
            if letter1==letter2:
                same_letter+=1
                break
    return same_letter            
