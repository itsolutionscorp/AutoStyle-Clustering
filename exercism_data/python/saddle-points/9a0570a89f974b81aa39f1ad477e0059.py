def saddle_points(s):
    
    if not s: return set()

    # Create lists to store our rows and columns in 
    rows = []; columns = [[] for i in range(len(s[0]))];

    # Create a name in which we will later use as a counter
    rowI = False

    # Parse the input
    for row in s:
        rows.append([])
        for i in range(len(row)):
            rows[-1].append(row[i])
            columns[i].append(row[i])

        # Check if there are an appropriate amount of items
        # in each input row
        if not rowI: rowI = len(row)
        elif rowI != len(row):
            raise ValueError("Each row in the input must contain an equal amount of items as the others.")

    # Define the amount of rows and columns
    rowL = len(rows); colL = len(columns);

    # Find the max item in each row. We must look
    # at each item, in case there are multiple items that
    # are the max
    ans1 = set()
    for i in range(rowL):
        for j in range(colL):
            if rows[i][j] == max(rows[i]):
                ans1.add((i,j))
                
    # Find the min item for eacn column, similar to
    # the previous iteration for the rows
    ans2 = set()
    for j in range(colL):
        for i in range(rowL):
            if columns[j][i] == min(columns[j]):
                ans2.add((i,j))

    # Our answer is the intersection of the columns/rows answer sets
    return ans1.intersection(ans2)
