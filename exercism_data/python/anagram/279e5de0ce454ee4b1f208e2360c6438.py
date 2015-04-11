def detect_anagrams(chkstr,candidates):
    
    letter_count  = count_letters(chkstr)
    result = list()
    for candidate in candidates:
        if candidate.lower() != chkstr.lower():
             if letter_count == count_letters(candidate):
                 result.append(candidate)
    return result

def count_letters(instr):
    result = dict()
    
    instr = instr.lower()
    for char in instr:
        if char in result:
            result[char] += 1
        else:
            result[char] = 1
            
    return result
