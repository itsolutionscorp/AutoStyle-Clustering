def calculate(s):
    
    # Assume we have the 'What is..' opening
    items = s.strip('What is ')
    
    # Rather than tokenizing the 'by' following multiplied
    # or divided (or other things), let's just remove it.
    # The phrase won't lose any detail for computation.
    for each in [' by',' to the',' power','th','nd','st','rd']:
        items = items.replace(each,"")

    # Find the first value to operate on
    items = items.split(' ',1)

    # Just incase someone asks a trivial question
    if '?' in items[0]:
        items[0] = items[0].strip('?')
        items.append('?')

    # Check if it is a digit
    if not items[0].strip('-').isdigit():
        raise ValueError("Incorrect sequence of items for wordy calculate.")

    # Store it as our current value
    ans = int(items[0])

    # Subsequent operations will operate on the above answer
    items = items[1].split(" ",2)

    # Continue operating until the end
    while '?' not in items[0]:
        
        # Remove a '?' from our value if it is
        # the final one
        if '?' in items[1]:
            items[1] = items[1].strip('?')
            items.append('?')

        # Check if the current operation and value are valid
        if not items[1].strip('-').isdigit():
            raise ValueError("Incorrect sequence of items for wordy calculate.")
        elif items[0] not in definitions():
            raise ValueError("Operation not found in definitions.")

        # Apply the operation to the values
        ans = definitions()[items[0]](ans,int(items[1]))

        # Parse the remaining input
        items = items[2].split(" ",2)        
        
    return ans

def definitions():
    return {'plus': lambda x,y:x+y,
            'minus': lambda x,y:x-y,
            'multiplied': lambda x,y:x*y,
            'divided': lambda x,y:x/y,
            'raised': lambda x,y:x**y
            }
