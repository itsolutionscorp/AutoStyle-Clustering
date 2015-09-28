def num_common_letters(goal_word, guess):
    count = 0
    guess_list = get_list(guess)
    goal_list = get_list(goal_word)
    for letter in guess_list:
        for goal_letter in goal_list:
            if(letter == goal_letter):
                count += 1
                goal_list.remove(goal_letter)
    return count
