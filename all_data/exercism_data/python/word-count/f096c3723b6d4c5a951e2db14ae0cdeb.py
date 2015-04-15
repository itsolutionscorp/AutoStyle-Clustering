#
# Word Count Implementation
#

def word_count(string):

# create output array
    output = {}

# split the string into an array of words
    array = string.split()
# get length of array
    array_len = len(array)

# iterate through each word in array
    for word in xrange(0, array_len):
# init word_count to 0
        inst_count = 0
# for each word in array, do a test to see if there is a match
        for inst in xrange(0, array_len):
# if match increment the count
            if array[word] == array[inst]:
                inst_count = inst_count + 1
# store the result into the output array
# overwiriting is ok since it will overwrite with same value
        output.update({array[word]: inst_count})

    return output
