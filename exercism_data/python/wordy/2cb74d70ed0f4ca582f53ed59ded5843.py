def calculate(s):
    
    # Assume we have the 'What is..' opening
    s = s.strip('What is ')
    # Rather than tokenizing the 'by' following multiplied
    # or divided, let's just remove it. The phrase won't
    # lose any cdetail for computation.
    for each in [' by',' to the',' power','th','nd','st','rd']:
        s = s.replace(each,"")

    # Divide our current phrase into four parts
    items = s.split(' ',3)

    if len(items) == 3 and items[2][-1] == '?':
        items[2] = items[2].strip('?')
        items.append('?')

    # Check for errors in input
    if not items[0].strip('-').isdigit() or not items[2].strip('-').isdigit():
        raise ValueError("Incorrect sequence of items for wordy calculate.")
    elif items[1] not in definitions():
        raise ValueError("Operation not found in definitions.")

    # Perform the first operation
    ans = definitions()[items[1]](int(items[0]),int(items[2]))

    # Subsequent operations will operate on the above answer
    s = items[3]
    items = s.split(" ",2)

    # Continue operating until the end
    while '?' not in items[0]:
        if '?' in items[1]:
            items[1] = items[1].strip('?')
            items.append('?')
        if not items[1].strip('-').isdigit():
            raise ValueError("Incorrect sequence of items for wordy calculate.")
        elif items[0] not in definitions():
            raise ValueError("Operation not found in definitions.")
        ans = definitions()[items[0]](ans,int(items[1]))

        s = items[2]
        items = s.split(" ",2)
        
        
    return ans

def definitions():
    return {'plus': lambda x,y:x+y,
            'minus': lambda x,y:x-y,
            'multiplied': lambda x,y:x*y,
            'divided': lambda x,y:x/y,
            'raised': lambda x,y:x**y
            }
