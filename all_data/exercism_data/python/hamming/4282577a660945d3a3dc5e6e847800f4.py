def distance(x, y):
    return sum([a != b for a, b in zip(x, y)])
# or
#def distance(x, y):
#    def not_equal(tpl):
#        return tpl[0] != tpl[1]
#    return sum(map(not_equal, zip(x, y)))
