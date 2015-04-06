def num_common_letters(goal_word, guess):
    goal_word = get_list(goal_word) 
    guess = get_list(guess)
    total = 0 
    nu_list = []
    for letter in goal_word:
        if letter in guess: 
            if letter not in nu_list: 
                nu_list.append(letter)
            total += 1 
    return total 
