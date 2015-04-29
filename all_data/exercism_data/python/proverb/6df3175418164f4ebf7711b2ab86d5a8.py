PART1 = "For want of a {} the {} was lost.\n"
PART2 = "And all for the want of a {}{}."


def proverb(my_list, qualifier=''):
    out1 = ''.join(PART1.format(item, my_list[i+1])
                   for i, item in enumerate(my_list[:-1]))

    qualifier += ' ' if qualifier else ''
    out2 = PART2.format(qualifier, my_list[0])

    return ''.join([out1, out2])
