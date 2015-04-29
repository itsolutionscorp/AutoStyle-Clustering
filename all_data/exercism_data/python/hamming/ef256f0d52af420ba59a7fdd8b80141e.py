import operator


def distance(strand_1, strand_2):
    if len(strand_1) != len(strand_2):
        raise ValueError('Strands must be of identical lengths')
    else:
        return len(strand_1) - sum(map(operator.eq, strand_1, strand_2))


# def distance(strand_1, strand_2):
#     if len(strand_1) != len(strand_2):
#         raise ValueError('Strands must be of identical lengths')
#     else:
#         dist = 0
#         for index, char in enumerate(strand_1):
#             if char != strand_2[index]:
#                 dist += 1
#         return dist
