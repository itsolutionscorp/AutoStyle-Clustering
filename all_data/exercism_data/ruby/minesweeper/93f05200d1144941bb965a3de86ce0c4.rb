class Board
  def self.transform(inp)
    new(inp).solve
  end

  def initialize(input)
    @board = input.dup
    validate_board
  end

  def solve
    each_index_pair do |row_index, cell_index|
      insert_count(row_index, cell_index)
    end

    board
  end

  private

  attr_reader :board

  def validate(assertion, message)
    raise ValueError.new(message) unless assertion
  end

  def validate_board
    validate line_lengths_equal?, "Unequal line lengths."
    validate border_complete?, "Invalid border."
    validate valid_characters?, "Invalid characters."
  end

  def line_lengths_equal?
    board.map(&:length).uniq.count == 1
  end

  def border_complete?
    valid_edges? && valid_top_and_bottom?
  end

  def valid_edges?
    board[1..-2].each do |line| 
      return false unless line[0] == "|" && line[-1] == "|"
    end
    true
  end

  def valid_top_and_bottom?
    [board[0], board[-1]].each do |line| 
      return false unless line[0]  == "+" && 
                          line[-1] == "+" && 
                          line[1..-2].chars.uniq == ["-"]
    end
    true
  end

  def valid_characters?
    each_index_pair do |row_index, col_index|
      return false unless [' ', MINE].include?(cell(row_index, col_index))
    end
  end

  def each_index_pair(&block)
    (1..board_height).each do |row_index|
      (1..board_width).each do |col_index|
        yield row_index, col_index
      end
    end
  end

  def board_height
    board.size - 2
  end

  def board_width
    board[0].length - 2
  end


  MINE = "*"

  def insert_count(row_index, cell_index)
    return if cell(row_index, cell_index) == MINE
    count = count_mines_around(row_index, cell_index)
    board[row_index][cell_index] = (count == 0 ? " " : count.to_s)
  end

  def count_mines_around(row_index, cell_index)
    surrounds(row_index, cell_index).count(MINE)
  end

  def surrounds(ri, ci)
    (-1..1).flat_map do |r|
      (-1..1).map {|c| cell(ri + r, ci + c) }
    end
  end

  def cell(ri, ci)
    board[ri][ci]
  end
end

class ValueError < ArgumentError
end
