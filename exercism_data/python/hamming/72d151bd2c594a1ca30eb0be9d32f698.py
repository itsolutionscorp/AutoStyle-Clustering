# First we take in two DNA strings and split them out into two lists
# of the individual characters. We also initalize a counter
# variable to hold the hamming distance. Then, we zip the two lists
# and use a for loop to go through each element in that zip and
# compare the two items. If they do not match, we add 1 to the
# hammingDistance counter variable. Then we return that variable.


def distance(inputStringA, inputStringB):
    """Takes in two DNA strings and Finds the Hamming Distance
       between the two.
    """

    dnaStringA = list(inputStringA)
    dnaStringB = list(inputStringB)
    hammingDistance = 0

    for itemA, itemB in zip(dnaStringA, dnaStringB):
        if itemA != itemB:
            hammingDistance += 1

    return hammingDistance
