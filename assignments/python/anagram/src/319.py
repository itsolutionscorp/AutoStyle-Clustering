def detect_anagrams(what,string_of_whats):
    count ={}
    
    #string_of_whats=[x.lower() for x in string_of_whats]
    #what = what.lower()
    count2 = {}
    playbook = []
    #counting the occurence of each character in the what string
    #and creating a histogramm (count)
    #using list comprehension to convert a list to all lowercase(for case insensitive comparison) but so as not to lose the original
    for char in [x.lower() for x in list(what)]:
        if char in count:
            count[char] += 1
        else:
            count[char] = 1
    for word in string_of_whats:
        #checking if it's the same word
        if word == what:
            return []
        #using list comprehension to convert a list member to all lowercase(for case insensitive comparison) but so as not to lose the original
        for char in [x.lower() for x in list(word)]:
            if char in count2:
                count2[char] += 1
            else:
                count2[char] = 1
        #adding to the list of anagramms
        if count == count2:
            playbook.append(word)
        count2 = {}
    return playbook
        
    print(count)
    print(string_of_whats)
