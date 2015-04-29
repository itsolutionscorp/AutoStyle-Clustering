def isAnagram(original, testable):

    # Check words are not the same
    if testable == original:
        return False

    # Ensure words are not a potential subset
    if len(testable) != len(original):
        return False

    # Ensure check letters are the same
    testable = testable.lower() # Make case insensitive
    original = original.lower()

    charPool = list(original)
    for char in testable:
        try:
            charPool.remove(char)
        except ValueError:
            return False

    return True

    
class Anagram(object):
    def __init__(self, baseWord):
        self.baseWord = baseWord    

    def match(self, testableList):
        return [word for word in testableList if isAnagram(self.baseWord, word)]
