def num_common_letters_second(x, y):
    i = 0
    seen_letters = []
    for letter in x:
        if (letter in y) and (letter not in seen_letters):
            seen_letters.append(letter)
            i += 1
    return i
