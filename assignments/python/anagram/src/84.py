def detect_anagrams(word,list_to_test):
    word_split = sorted(list(word.lower()))
    list_of_answers = []
    
    for entry in list_to_test:
        if entry.lower() == word.lower():
            continue
        if sorted(list(entry.lower()))==word_split:
            list_of_answers.append(entry)
    return list_of_answers
