''' @author: Flavio Miranda '''
_points = {1: ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
           2: ['D', 'G'],
           3: ['B', 'C', 'M', 'P'],
           4: ['F', 'H', 'V', 'W', 'Y'],
           5: ['K'],
           8: ['J', 'X'],
           10: ['Q', 'Z']
}


def score(word):
    return sum([point for letter in word for point in _points.keys() if letter.upper() in _points[point]])
