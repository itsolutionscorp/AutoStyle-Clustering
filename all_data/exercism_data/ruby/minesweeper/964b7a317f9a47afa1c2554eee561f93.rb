class Board
  
  NEIGHBORS = [:left,
               :right,
               :top_left,
               :top,
               :top_right,
               :bottom_left,
               :bottom,
               :bottom_right]

  def self.transform(board)
    validate_board(board)

    board.each_with_index do |row, r|
      row.chars.each_with_index do |cell, c|
        board[r][c] = count_neighbors(board,r,c).to_s.tr("0"," ") if cell == " "
      end
    end
  end

  private

  def self.validate_board(board)
    raise ValueError if board.any? do |row|
      wrong_length?(row, board) || faulty_border?(row) || has_invalid_char?(row)
    end
  end

  def self.wrong_length?(row, board)
    row.size != board[0].size
  end

  def self.faulty_border?(row)
    !row.match(/(^\|.*\|$)|(^\+-*\+$)/)
  end

  def self.has_invalid_char?(row)
    row.match(/[^\+\-\|\*\s0-9]/)
  end

  def self.count_neighbors(board, row, pos)
    NEIGHBORS.inject(0) do |sum, direction|
      mine_at?(direction, board, row, pos) ? sum + 1 : sum
    end
  end

  def self.mine_at?(direction, board, row, pos)
    case direction
    when :left then board[row][pos - 1] == "*" 
    when :right then board[row][pos + 1] == "*"
    when :top_left then board[row - 1][pos - 1] == "*"
    when :top then board[row - 1][pos] == "*"
    when :top_right then board[row - 1][pos + 1] == "*"
    when :bottom_left then board[row + 1][pos - 1] == "*"
    when :bottom then board[row + 1][pos] == "*"
    when :bottom_right then board[row + 1][pos + 1] == "*"
    end
  end

end
class ValueError < StandardError; end
