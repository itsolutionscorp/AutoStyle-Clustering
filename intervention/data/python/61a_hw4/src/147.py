def num_common_letters(goal_word, guess):
    letters_checked=[]  #this is unecessary if goal_word alwys gets input
                        #before the guess, but i'll leave it because
                        #if i mess it up, it'll still check properly
    letters_in_common=0
    goal_string=get_string(goal_word)
    guess=get_string(guess)
    for letter in goal_string:
        if letter in guess and not letter in letters_checked:
            letters_in_common+=1
        letters_checked+=[letter]#same comment for letters_checked
    return letters_in_common
