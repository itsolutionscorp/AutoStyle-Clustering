""" Exercism - Anagram """

def detect_anagrams(target, tests):
    """
    Separates out the items in tests and runs each
    one through letters_equal to determine if they
    are the same individually
    """
    anagrams = []
    for test in tests:
        if letters_equal(target, test) and not words_identical(target, test):
            anagrams.append(test)
    return anagrams


def letters_equal(target, test):
    """
    Compares the count of the letters within the two
    strings to confirm that each string has the same
    number of each.
    """
    for letter in target.lower() + test.lower():
        if target.lower().count(letter) != test.lower().count(letter):
            return False
    return True

def words_identical(target, test):
    """
    Checks to see if target and test are equal (after
    controlling for case)
    """
    return target.lower() == test.lower()
