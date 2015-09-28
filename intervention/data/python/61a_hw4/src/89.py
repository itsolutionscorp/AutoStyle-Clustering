def num_common_letters(goal_word, guess):
    goalword_list = get_list(goal_word)
    guess_list = get_list(guess)
    result = 0
    count_repeats = 0 
    guessed_letters = [] 
    for i in range(len(guess_list)):
        if guess_list[i-1] == guess_list[i]:
            repeated = [guess_list[i]] 
            guessed_letters = guessed_letters + repeated 
            count_repeats += 1
    for letter in goalword_list:
        for letter_1 in guess_list:
            if letter == letter_1:
                result += 1 
    return result - count_repeats 
