'''exer saddle_points'''


def matches(val, lst):
    '''return all positions in lst where val occurs'''
    return [i for i, lval in enumerate(lst) if lval == val]

def extremes(icmp, lst):
    '''return winning value of list, determined by iterative comparison function
    icmp, and list of all locations where it occurs'''
    extreme_val = icmp(lst)
    return extreme_val, matches(extreme_val, lst)

def saddle_points(lst_of_rows):
    '''given matrix input as lst_of_rows, find all saddle points'''
    saddles = []
    # make list of cols and guard against irregular matrices
    lst_of_cols = []
    if lst_of_rows:
        num_cols = len(lst_of_rows[0])
        ndx = 0     # guard against undefined val in Irreg Matrix check below
    try:
        for col in lst_of_rows[0]:
            lst_of_cols.append([col])
        for row in lst_of_rows[1:]:
            for ndx, col in enumerate(row):
                lst_of_cols[ndx].append(col)
            if ndx != num_cols - 1:
                raise ValueError('Irregular Matrices not Supported')
    except IndexError:
        # no values given
        pass

    # build dataset for max row vals and min col vals used in comparison phase
    maxs = []
    for row in lst_of_rows:
        maxs.append((extremes(max, row)))  # (rowval, [col locs])
    mins = []
    for col in lst_of_cols:
        mins.append((extremes(min, col)))  # (colval, [row locs])

    # compare structures, append finds, some duplicates will happen
    for rval, cndxs in maxs:
        for cndx in cndxs:
            cval, rndxs = mins[cndx]
            if cval == rval:
                for rndx in rndxs:
                    saddles.append((rndx, cndx))

    # use sets to filter dupes
    return set(saddles)
