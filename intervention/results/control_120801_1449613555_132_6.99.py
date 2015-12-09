def num_common_letters(goal_word, guess):
    common_letters = []
    for character in guess:
        if character in goal_word and character not in common_letters:
            common_letters.append(character)
    return len(common_letters)