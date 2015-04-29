
def detect_anagrams(candidate, wordArray):
    anagrams = []
    candidate = candidate.lower()
    for i in range(0, len(wordArray)): #calls "sortLetters" and testsfor equality, excludes the candidate word from the results
        if sortLetters(candidate) == sortLetters(wordArray[i].lower()) and candidate.lower() != wordArray[i].lower():          
            anagrams.append(wordArray[i])
    return anagrams

def sortLetters(text): #Arranges the letters of each word alphabetically
    return ' '.join(sorted(text))
