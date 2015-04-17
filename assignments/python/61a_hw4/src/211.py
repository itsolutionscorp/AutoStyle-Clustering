def num_common_letters(goal_word, guess):
    counter = 0
    s1, s2 = sorted(goal_word[0]), sorted(guess[0])
    n1, n2 = '', ''
    for el in s1: #remove all repeating letters in the goal_word
        if el not in n1:
            n1 += el
    for el in s2: #remove all repeating letters in the guess
        if el not in n2:
            n2 += el
    for i in range(len(n1)):
        for j in range(len(n2)):
            if s1[i] == s2[j]:
                counter += 1
    return counter
