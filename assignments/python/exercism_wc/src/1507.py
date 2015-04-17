def word_count(string):
    output = {}
    array = string.split()
    array_len = len(array)
    for word in xrange(0, array_len):
        inst_count = 0
        for inst in xrange(0, array_len):
            if array[word] == array[inst]:
                inst_count = inst_count + 1
        output.update({array[word]: inst_count})
    return output
