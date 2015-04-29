# Calculates the hamming distance between two homologous strands of DNA


def distance(first_string, second_string):

    differences = (char_1 != char_2
                   for char_1, char_2 in zip(first_string, second_string))

    return sum(differences)
