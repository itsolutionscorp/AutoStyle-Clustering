def num_common_letters(goal_word, guess):
    goal_word_list = get_list(goal_word)
    guess_list = get_list(guess)
    if goal_word_list == guess_list:
        return len(guess_list)
    same,i = 0,0
    while i < len(goal_word_list):
        index = 0
        while index < len(guess_list) and i < len(goal_word_list):
            if goal_word_list[i] == guess_list[index]:
                same += 1
                i += 1
                index = 0
            else:
                index += 1
        i += 1
    return same
        
