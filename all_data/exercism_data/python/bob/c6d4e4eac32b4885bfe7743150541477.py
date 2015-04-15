#
# Skeleton file for the Python "Bob" exercise.
#


def isYelling(sentence):
    """
    Checks if there is more than 1 uppercase word excluding 'OK' and 'DMV'. If
    more than 1 are present, the sentence is being yelled at.
    """
    sentence= sentence[:len(sentence)-1]
    words = sentence.strip().split()
    if len(words) >  0:
        upperCount = 0
        constCount = 0
        for word in words:
            if word == "OK" or word == "DMV":
                constCount += 1
        for word in words:
            if word.isupper():
                upperCount += 1
        upperCount -= constCount
        if upperCount >= 1:
            return True
        else:
            return False
    else:
        return False


def isQuestion(sentence):
    """
    Returns True is the sentence is not being yelled and the last character is a
    '?'
    """
    if not isYelling(sentence):
        sentence = sentence.strip()
        if len(sentence) > 0:
            return sentence[len(sentence) - 1] == "?"
        else:
            return False
    else:
        return False


def isSilence(sentence):
    """
    Returns True is passed string is empty string.
    """
    # Stripping whitespace to make sure no special sace charecters were passed
    sentence = sentence.strip()
    return len(sentence) == 0


def hey(what):
    """
    Checks what kind of sentence was passed to method and returns an appropriate
    response.
    """
    response = ""
    if isQuestion(what):
        response = "Sure."
    elif isYelling(what) is True:
        response = "Whoa, chill out!"
    elif isSilence(what):
        response = "Fine. Be that way!"
    else:
        response = "Whatever."

    return response

if __name__ == '__main__':
    print(hey("OK are you gong to the DMV?"))
