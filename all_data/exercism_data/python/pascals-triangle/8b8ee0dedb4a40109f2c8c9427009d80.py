def triangle(stop=0):
    output = []
    lastrow = []

    for i in range(stop + 1):
        newrow = []

        for index in range(i + 1):
            if index == 0:
                newrow.append(1)
            else:
                try:
                    left = lastrow[index - 1]
                except IndexError:
                    left = 0

                try:
                    right = lastrow[index]
                except IndexError:
                    right = 0

                newrow.append(left + right)

        lastrow = newrow
        output.append(' '.join(str(i) for i in newrow))

    return output


def row(num):
    return triangle(num)[num]


def is_triangle(inp):
    return inp == triangle(len(inp) - 1)
