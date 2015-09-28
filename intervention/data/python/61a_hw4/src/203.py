def num_common_letters(goal_word, guess):
    commonLetters = 0
    for letter in get_list(goal_word):
        if(letter in get_list(guess)):
            commonLetters += 1
    return commonLetters
