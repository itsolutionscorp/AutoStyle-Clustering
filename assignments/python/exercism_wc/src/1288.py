def word_count(userInput):
    """
    :type userInput: str
    """
    userInput = userInput.replace("\n", " ")
    userInput = userInput.split(" ")
    while "" in userInput:
        userInput.remove("")
    occurrences = {}
    for i in userInput:
        if i in occurrences:
            occurrences[i] += 1
        elif i not in occurrences:
            occurrences[i] = 1
    return occurrences