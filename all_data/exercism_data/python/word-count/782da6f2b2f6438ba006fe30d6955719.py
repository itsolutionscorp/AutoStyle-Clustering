def word_count(string):
    
    results = {}
    string_array = string.split()

    while string_array:
        s = string_array[0]
        c = string_array.count(s)
        results[s] = c
        for i in range(c):
            string_array.remove(s)

    return results
        
        
        
