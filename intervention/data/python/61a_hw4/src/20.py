def num_common_letters(goal_word, guess):
    goal_list = get_list(goal_word)
    guess_list = get_list(guess) 
    matching_elements = [elem for elem in goal_list if elem in guess_list]
    return len(matching_elements)
