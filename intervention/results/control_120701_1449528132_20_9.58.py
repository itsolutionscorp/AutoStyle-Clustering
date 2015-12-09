def common_letters_second(x, y):
    i = 0
    seen_letters = []
    for letter in x:
        if (letter in y) and (letter not in seen_letters):
            i += 1
            seen_letters.append(letter)
    return i
