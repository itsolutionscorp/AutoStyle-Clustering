def board(board_content):
    row_count = 0
    col_count = 0
    for row in board_content:
        col_count = 0
        new_row = str()
        for col in row:
            new_row += str(find_value(row_count, col_count, board_content))
            col_count = col_count + 1
        board_content[row_count] = new_row
        row_count = row_count + 1
    return board_content

def find_value(row_value, col_value, board_content):
    accepted_strings = ['*', '|', '+','-']
    if board_content[row_value][col_value] in accepted_strings:
        return board_content[row_value][col_value]
    elif board_content[row_value][col_value] == ' ':
        count_val = 0
        try:
            for row_curr in range(row_value-1,row_value+2):
                for col_curr in range(col_value-1,col_value+2):
                    if board_content[row_curr][col_curr] == '*':
                        count_val = count_val + 1
        except:
            raise ValueError
        if count_val ==0:
            count_val = ' '
        return count_val
    else:
        raise ValueError
