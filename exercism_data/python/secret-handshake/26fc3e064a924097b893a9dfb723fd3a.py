def handshake(n):
    
    # We must be able to handle three classes of inputs:
    
    # 1. the input is a binary representation in a string
    if isinstance(n,str):
        # ..which we must also check to see if it is
        # valid or not, which for this problem
        # also includes mandating that the maximum input
        # would be '11111', or a string of length 5
        if n.count('1') + n.count('0') != len(n) or len(n) > 5:
            return []
        else:
            b = int(n)
        
    # 2. We can't handle negative numbers, so it is an invalid
    #    input
    elif n < 0:
        return []
    
    # 3. A valid integer input can then be transformed to a
    #    binary representation, but let's make it an integer
    #    to operate upon
    else:
        b = int(bin(n).split('b')[-1])

    # If the integer representation of the binary
    # is above 10000 we must retain reversal in the answer
    if b >= 10000:
        b -= 10000
        r = True
    else:
        r = False

    # Let's iterate over the possible words in reverse,
    # as it will make the iteration easier
    d = [[1000,'jump'],
         [100,'close your eyes'],
         [10,'double blink'],
         [1,'wink']]

    # Store our answer in a list
    ans = []

    # For each possible word
    for each in d:
        # See if b is greater or equal to it
        if b >= each[0]:
            # If so, decrement b and
            # add the appropriate word to the answer
            b -= each[0]
            ans.append(each[1])

    # Since we iterated over the words in reverse,
    # we only need to use the 'reverse' function
    # if the input indicated NOT to be reversed.
    if not r:
        ans.reverse()

    return ans

def code(l):
    # Store our word -> bit mapping to a dictionary
    d = {'jump':1000,
         'close your eyes':100,
         'double blink':10,
         'wink':1}
    # our 'binary' counter
    b = 0

    # For each item in the input list
    for each in l:
        
        # If we find a word that shouldn't belong,
        # we have an inproper input
        if each not in d: return '0'
        
        # Otherwise increment b by the indicated mapping
        b += d[each]

    # Check to see if we need to include the reversal condition
    
    # First, a list of items in the reverse order
    d = ['jump','close your eyes','double blink','wink']
    
    # Then check every item of the input against the items
    # following it
    for i in range(len(d)-1):
        for j in range(i,len(d)):
            if d[i] in l and d[j] in l:
                if l.index(d[i]) < l.index(d[j]):
                    b += 10000
                    break
        if b >= 10000:
            break

    try:
        int(str(b),2)
        
        # b is an int, but we must return a string of b
        return str(b)
    except:
        raise ValueError, "Input to the function must be a valid sequence of words."
