import re


def detect_anagrams(sub_choice, choices):
    almost_there = [choice for choice in choices
                    if re.search(r'[{}]{{{}}}'.format(sub_choice, len(sub_choice)), choice, re.IGNORECASE)
                    and len(choice) == len(sub_choice)
                    and choice.lower() != sub_choice.lower()]

    # really don't like this, the list comprehension passes all tests but one...
    # the loop below is to verify that the regex doesn't confuse different duplicates
    for word in almost_there:
        for char in sub_choice:
            if word.lower().count(char) != sub_choice.lower().count(char):
                almost_there.pop(almost_there.index(word))
                break  # if we pop'd once, we don't want to continue iterating
    return almost_there
