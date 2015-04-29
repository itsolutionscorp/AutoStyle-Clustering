# Calculates the hamming distance between two homologous strands of DNA


def distance(first_string, second_string):

    differences = [first_string[i] != second_string[i]
                   for i in range(len(first_string))]

    return sum(differences)
