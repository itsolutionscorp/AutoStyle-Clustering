def to_rna(input):

    # replace original values with placeholders
    input = input.replace('G', '1') \
                 .replace('C', '2') \
                 .replace('T', '3') \
                 .replace('A', '4')

    # replace placeholders with final values
    input = input.replace('1', 'C') \
                 .replace('2', 'G') \
                 .replace('3', 'A') \
                 .replace('4', 'U')

    return input
