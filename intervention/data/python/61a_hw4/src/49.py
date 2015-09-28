def num_common_letters(goal_word, guess):
    lst = []
    a = get_string(goal_word)
    b = get_string(guess)
    for x in range(len (get_string (goal_word))):
        for y in range(len(get_string(goal_word))): 
            if(a[x] == b[y]): 
                lst += a[x]
                break
    counter = len(lst)
    return counter 
