def num_common_letters(goal_word, guess):
    guess=get_string(guess)
    goal_word=get_string(goal_word)
    temp_char=''
    count=0
    for i in range(0,len(guess)):
        if (guess[i] in goal_word) and temp_char!=guess[i]:
            count+=1
            temp_char=guess[i]
    return count
