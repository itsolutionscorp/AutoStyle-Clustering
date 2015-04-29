class ValueError < StandardError;  end;
class Board
  
  attr_accessor :input, :numbers

  def initialize(input)
    self.input = input
    validate
    init_numbers
    numeric_hints
  end
  
  def validate
    raise ValueError unless /\+-+\+/.match(input.first) && /\+-+\+/.match(input.last)
    input.each do |row|
      raise ValueError unless row.length == input.first.length
      unless row == input.first || row == input.last then
        raise ValueError unless /^(\|){1}(\s|\*)+(\|){1}$/.match(row)
      end
    end 
  end
  
  def init_numbers
    rows = input.length
    cols =  input[0].length 
    self.numbers = Array.new(rows) {"|"+" "*(cols-2)+"|"}
    #set borders
    numbers[0] = input.first
    numbers[numbers.length-1] = input.last
  end
  
  def numeric_hints
    out = numbers
    for row in 0..input.length-1
       unless (row == 0 || row == input.length-1) then
        for col in 1..input[row].length-2
          if mine?(input[row][col]) then
            out[row][col] = "*"
          else  
           mine_count = count_mines(row,col)
           if mine_count > 0
             out[row][col] = count_mines(row,col).to_s
           end   
          end 
        end
       end
    end
    out
  end  
  
  def count_mines(row,col)
    count = 0
    row_below = row + 1
    row_above = row - 1
    last_row = input.length - 2
    first_row = 1
    case row
    when first_row
      count += self.count_horizontal(row,col)
      count += self.count_diagonal_right_below(row,col)
      count += self.count_diagonal_left_below(row,col)
      count += self.count_vertical(row,col)
    when last_row
      count += self.count_above(row,col)      
      count += self.count_horizontal(row,col)
      count += self.count_diagonal_right_above(row,col)
      count += self.count_diagonal_left_above(row,col)
    else
      count += self.count_above(row,col)      
      count += self.count_horizontal(row,col)
      count += self.count_diagonal_right_below(row,col)
      count += self.count_diagonal_left_below(row,col)
      count += self.count_diagonal_right_above(row,col)
      count += self.count_diagonal_left_above(row,col)
      count += self.count_vertical(row,col)
    end
    count
  end
  
  def count_horizontal(row,col)
    count = 0
    first_col = 1
    next_col = col + 1
    prev_col = col - 1
    last_col = input[row].length-2
    case col
    when 0,last_col+1
      #skip
    when first_col 
      count +=1 if mine?(input[row][next_col])
    when input[row][last_col]
      count +=1 if mine?(input[row][prev_col])
    else 
      count +=1 if mine?(input[row][prev_col])
      count +=1 if mine?(input[row][next_col])
    end
    count
  end
  
  def count_diagonal_right_below(row,col)
    return 1 if mine?(input[row+1][col+1])
    0
  end    
  
  def count_diagonal_left_below(row,col)
    return 1 if mine?(input[row+1][col-1])
    0
  end
  def count_diagonal_right_above(row,col)
    return 1 if mine?(input[row-1][col-1])
    0
  end    
  
  def count_diagonal_left_above(row,col)
    return 1 if mine?(input[row-1][col+1])
    0
  end   

  
  def count_vertical(row,col)
    return 1 if mine?(input[row+1][col])
    0
  end 
  
  def count_above(row,col)
    return 1 if mine?(input[row-1][col])
    0
  end   
  
  def mine?(char)
    char == "*"
  end   
  
  
  def self.transform(input)  
    Board.new(input).numbers
  end 
  
end  
