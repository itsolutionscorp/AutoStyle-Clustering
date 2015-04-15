def hey(words):
    letters = "qwertyuiopasdfghjklzxcvbnm"
    letters = letters + letters.upper()
    numbers = "1234567890"
    has_letters = False
    has_numbers = False
    
    for char in words:
        for letter in letters:
            if char == letter:
                has_letters = True
        for number in numbers:
            if char == number:
                has_numbers = True
                
    if words == words.upper() and words != "" and has_letters:
        return "Whoa, chill out!"
    
    elif words[-1:] == "?":
        return "Sure."
    
    elif not has_letters and not has_numbers:
        return "Fine. Be that way!"
        
    else:
        return "Whatever."
