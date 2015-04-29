#
# Skeleton file for Exercism Word Count exercise.
#


def word_count(phrase):
    phrase = phrase.split()
    lista = []

    for i in phrase:
        count = 0
        if i not in lista:
            x = phrase.count(i)
            lista.append(i + ': ' + x )

        else:
            continue

    return lista
