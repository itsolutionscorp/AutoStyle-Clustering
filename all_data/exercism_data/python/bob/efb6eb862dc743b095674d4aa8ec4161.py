# What he answers when nothing is said
address = 'Fine. Be that way!'

# What he answers when asked a question
question = 'Sure.'

# What he answers when shoated at
shout = 'Whoa, chill out!'

# what he answers to anythin else
anything = 'Whatever.'

def hey(message):
    message = message.strip()
    
    # Nothing was said
    if message == '':
        return address
    
    # Detects if shouted at
    shouted = 1
    is_letter = 0
    for letter in message:
        # Either space or punctuation
        if not letter.isalnum():
            continue
        
        # Ensures at least one upper case letter
        if is_letter == 0 and letter.isalpha():
            is_letter = 1
            
        # Letter isn't upper case, can't be shouting.
        if letter.islower():
            shouted = 0
            break
            
    # He was shouted at
    if not is_letter == 0 and shouted == 1:
        return shout
    
    # Detects question (Last letter is ?)
    last = len(message) - 1
    if message[last] == '?':
        return question
        
    # Doesn't fit spec
    return anything
