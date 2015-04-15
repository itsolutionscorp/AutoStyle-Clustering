def slices(num, size):
    if size > len(num) or size == 0:
        raise ValueError

    # Creates a List of Lists with nested list comprehension.
    # Loops through the string to get the initial index for our inner list.
    # Iterates over the proper slice of num converting the character into int.
    return [[int(n) for n in num[i:i+size]] for i in range(0, len(num)-size+1)]
