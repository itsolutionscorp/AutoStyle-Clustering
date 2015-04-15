#series.py
# serving up a fresh slice of code


def slices(series, size):
    if (len(series)-size) < 0 or size == 0:
        raise ValueError('Slice is too large(or zero!?), just eat the whole thing why don\'t you?')
    return [map(int, list(series[i:size+i])) for i in range(0, (len(series)-size)+1)]
    #functional and readable to the python elite
    #but lets break it down:  for i in range(0, (len(series)-size)+1) gets us the current index of the first element
    # of our slice
    #list(series[i:size+i])) turns our slice from ['xyz'] into ['x', 'y', 'z']
    #map() turns ['x', 'y', 'z'] into [x, y, z]
    #
    #Fredrik Lundh once suggested the following set of rules for refactoring uses of lambda:
    #Write a lambda function.
    #Write a comment explaining what the heck that lambda does.
    #Study the comment for a while, and think of a name that captures the essence of the comment.
    #Convert the lambda to a def statement, using that name.
    #Remove the comment.
    # from https://docs.python.org/2/howto/functional.html
