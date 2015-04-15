ops = {'1':'wink', '2':'double blink', '4':'close your eyes', '8':'jump'}

def handshake(number):
#converts a given number into a list of operations based on ops dictionary
    operations = []
    if int(number) > 0:    
        num = bin_change(number)
        if num == None:
            return operations
        for k, v in sorted(ops.iteritems()):
            if int(num) & int(k) != 0:
                operations.append(v)
        if int(num) & 16 == 16:
            operations = list(reversed(operations))
    return operations
                  
def code(operations):
#converts a given list of operations into a number based on ops dictionary
    nums = []
    for x in operations:
        for k, v in sorted(ops.iteritems()):
            if x == v:
                nums.append(int(k))
    if len(nums) != len(operations):
        return '0'
    if nums == sorted(nums):
        return (bin(sum(nums)))[2:]
    else:
        return (bin(sum(nums+[16])))[2:]
    
def bin_change(number):
#checks to see the given format of a number (bin, int, str), converts it to \
#necessary format: binary representation of integer
    try:
        return int(str(number), 2)
    except ValueError:
        if number == str(number):
            return None
        return int(number)
