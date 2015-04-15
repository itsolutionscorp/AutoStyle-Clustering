def board(b):
    rows = []
    out = b[:]
    
    if b[0][0] != '+' or b[0][-1] != '+' or b[0].count('+')+b[0].count('-') != len(b[0]):
        raise ValueError('First row has a problem.')
    if b[-1][0] != '+' or b[-1][-1] != '+' or b[0].count('+')+b[0].count('-') != len(b[-1]):
        raise ValueError('Last row has a problem.')
    
    rows.append(b[0])
    for line in b[1:-1]:
        
        if line[0] != '|' or line[-1] != '|' or len(line) != len(b[0]):
            raise ValueError('Row number ' + str(len(rows)) + ' has a problem.')
        
        rows.append(['|'])
        
        for character in line[1:-1]:
            
            if character != ' ' and character != '*':
                raise ValueError('Row number ' + str(len(rows)-1) + ' has a problem.')
            rows[-1].append(character)
            
        rows[-1].append('|')
        
    rows.append(b[-1])

    for i in range(1,len(rows)-1):
        
        for j in range(1,len(rows[0])-1):
            
            if rows[i][j] == '*': continue
            
            value = 0
            if rows[i][j+1] == '*': value += 1
            if rows[i][j-1] == '*': value += 1
            if rows[i+1][j] == '*': value += 1
            if rows[i-1][j] == '*': value += 1
            if rows[i+1][j+1] == '*': value += 1
            if rows[i-1][j+1] == '*': value += 1
            if rows[i+1][j-1] == '*': value += 1
            if rows[i-1][j-1] == '*': value += 1

            if value > 0 and rows[i][j] == ' ':
                rows[i][j] = str(value)

        out[i] = ''.join(rows[i])
            
    return out
