def num_common_letters(goal_word, guess):
    non_repeated, result = [], 0
    guess_letters, goal = get_list(guess), get_list(goal_word)
    for letter in range (0, len(guess_letters)):
        if not guess_letters[letter] in non_repeated:
            non_repeated += guess_letters[letter]
        letter += 1
    for letter in range(0, len(non_repeated)):
        for l in range(0,len(goal)): 
            if non_repeated[letter] == goal[l]:
                result += 1
            l+= 1
        letter += 1
    return result 
