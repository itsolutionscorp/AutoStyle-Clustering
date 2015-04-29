def num_common_letters(goal_word, guess):
    string1, string2, count = get_string(goal_word), get_string(guess), 0 
    for letter in string1:
        if letter in string2:
            count +=1
    return count
