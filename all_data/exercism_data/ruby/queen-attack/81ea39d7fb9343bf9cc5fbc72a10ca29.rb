class Queens
  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3])
    @white = white
    @black = black
    cannot_occupy_same_space
    cannot_be_positioned_outside_the_board
  end

  def to_s
    board = new_board

    place_white(board)
    place_black(board)

    split_into_columns(board)
  end

  def attack?
    if same_row? || same_column?
      true
    elsif regular_diagnol?
      true
    elsif adjacent_diagnol?
      true
    elsif cardinal_diagnol?
      true
    end
  end

  private

  def cannot_occupy_same_space
    if black == white
      raise ArgumentError.new
    end
  end

  def cannot_be_positioned_outside_the_board
    if black.any? { |i| i > 7 } || white.any? { |i| i > 7 }
      raise ArgumentError.new
    end
  end

  def new_board
    board = []

    8.times do
      row = []
      8.times do
        row << 'O'
      end
      board << row
    end

    board
  end

  def split_into_columns(board)
    board.map do |rows|
      rows.join(" ")
    end.join("\n")
  end

  def place_white(board)
    board[white.first][white.last] = 'W'
  end

  def place_black(board)
    board[black.first][black.last] = 'B'
  end

  def same_row?
    white.first == black.first
  end

  def same_column?
    white.last == black.last
  end

  def regular_diagnol?
    (white.first + black.first) == (white.last + black.last)
  end

  def adjacent_diagnol?
    adjacent_rows? && adjacent_columns?
  end

  def adjacent_rows?
    (white.first.next == black.first) || (black.first.next == white.first)
  end

  def adjacent_columns?
    (white.last.next == black.last) || (black.last.next == white.last)
  end

  def cardinal_diagnol?
    cardinal_rows? && cardinal_columns?
  end

  def cardinal_rows?
    ((white.first.next + 1) == black.first) || ((black.first.next + 1) == white.first)
  end

  def cardinal_columns?
    ((white.last.next + 1) == black.last) || ((black.last.next + 1) == white.last)
  end
end
