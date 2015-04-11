def distance(one, another):
    return len([False for o_elm, a_elm in zip(one, another) if not o_elm == a_elm])
