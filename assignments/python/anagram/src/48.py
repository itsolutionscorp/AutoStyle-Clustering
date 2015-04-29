#-----------------------------------------
# Name:        Mcface3000
# Purpose:      Anagrama-banana-mam!
#-----------------------------------------
def detect_anagrams(variable, variable2):
    count = 0
    logic = 0
    words = []
    comparison = {}
    comparison2 = {}
    #Places all the letters from variable 1 into the dictionary comparison
    for i in variable:
        comparison[i] = variable.count(i)
    #Places all the letters from variable 2 into the dictionary comparison 2
    for i in variable2[count]:
        comparison2[i] = variable2[count].count(i)
    #Compares two dictionaries, comparison/comparison2 is the variable name
    shared_items = set(comparison.items()) & set(comparison2.items())
    #Determines how many shared items the two dictionaries have in common
    #Compares that number against the length of dictionary one
    #(if length is equal to shared items, they're the same word)
    if len(shared_items) != len(comparison):
    #Incrementing the logic effectiely removes the word from being appended to Words
        logic += 1
    #Loops a FOR variable until count equals the length of variable 2 (to check all of the letters)
    while count < len(variable2):
        if len(variable) == len(variable2[count]):
            for i in variable.lower():
                if variable.lower() == variable2[count].lower():
                    logic += 1
                if i in variable2[count].lower():
                    logic += 0
                if i not in variable2[count].lower():
                    logic += 1
            if logic == 0:
            #Appends variable2 (indexed by its count) to the Words list
                words.append(variable2[count])
        logic = 0
        count += 1
    return words
