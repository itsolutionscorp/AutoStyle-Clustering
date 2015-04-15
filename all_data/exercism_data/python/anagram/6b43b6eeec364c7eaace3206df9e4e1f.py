def detect_anagrams(anagram, phrase_list):
    anagram_list = [];
    anagram_dict = {};
    
    fill_dict(anagram.lower(), anagram_dict);

    for word in phrase_list:
        if word.lower() == anagram.lower():
            continue;

        temp_dict = {};
        fill_dict(word.lower(), temp_dict);
        found = False;

        if cmp(anagram_dict, temp_dict) == 0:
            found = True;
 
        if found == True:
            anagram_list.append(word);

    return anagram_list; 

def fill_dict(anagram, my_dict):
    for i in range(len(anagram)):
        if my_dict.has_key(anagram[i]):
            my_dict[anagram[i]] = my_dict[anagram[i]] + 1;
        else:
            my_dict[anagram[i]] = 1;
    
