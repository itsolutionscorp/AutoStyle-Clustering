def num_common_letters(goal_word, guess):
    gword=get_list(make_word_from_string(goal_word)) 
    guesser=get_list(make_word_from_string(guess))
    #get_list makes a python list of individual letters
    return checker(gword, guesser)
def checker(lst1, lst2):
        if lst1[1:]==[]:
            if lst1[0] in lst2:
                return 1
            return 0
        if lst1[0] not in lst2:
            return checker(lst1[1:],lst2)
        else:
            return 1+checker(lst1[1:],lst2)
