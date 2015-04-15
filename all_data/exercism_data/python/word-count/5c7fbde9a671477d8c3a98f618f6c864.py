def word_count(string):
    
    results = {}
    string_array = string.split()

    for s in string_array:
        if s not in results:
            results[s] = 1
        else:
            results[s] += 1
    return results
        
        
        
