def board(mines):
    if border_error(mines):
        raise ValueError('Invalid border.')
    w = len(mines[0])
    h = len(mines)
    convert_to_list(mines, h, w)
    nums = calc_neighborhoods(mines, h, w)
    return convert_to_string(mines, nums, h, w)

def border_error(m):
    corners = m[0][0] + m[0][-1] + m[-1][0] + m[-1][-1]
    if corners.strip('+'):
        return True
    if m[0].strip('+').strip('-'):
        return True
    if m[-1].strip('+').strip('-'):
        return True
    for i in range(1, len(m)-1):
        if m[i][0] != '|' or m[i][-1] != '|':
            return True
    return False

def convert_to_list(mines, h, w):
     for i in range(h):
        mines[i] = list(mines[i])
        if len(mines[i]) != w:
            raise ValueError('Board not rectangular.')
            
def calc_neighborhoods(mines, h, w):
    nums = []
    for i in range(h):
        nums.append([0]*w)
    for i in range(1,h-1):
        for j in range(1,w-1):
            if mines[i][j] == '*':
                for k in range(i-1, i+2):
                    for l in range(j-1, j+2):
                        nums[k][l] += 1
            elif mines[i][j] != ' ':
                raise ValueError('"%s" is not a valid character.', mines[i][j])
    return nums
    
def convert_to_string(mines, nums, h, w):
    for i in range(1,h-1):
        for j in range(1,w-1):
            if mines[i][j] == ' ' and nums[i][j] > 0:
                mines[i][j] = str(nums[i][j])
    for i in range(h):
        mines[i] = ''.join(mines[i])
    return mines
