def num_common_letters(goal_word, guess):
    goal_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    letters, counter = 0, 0
    letters_lst = []
    for counter in range(len(goal_lst)): 
        for index in range(len(guess_lst)):
            if guess_lst[index] == goal_lst[counter]:
                if guess_lst[index] not in letters_lst:
                    letters += 1
                    letters_lst = letters_lst + list(guess_lst[index])    
        counter += 1
    return letters
