class Queens
  def initialize(white: [0, 3], black: [7, 3])
    @white = white
    @black = black
    raise ArgumentError if @white == @black
  end

  def white
    @white
  end

  def black
    @black
  end

  def to_s
    board = []
    (0..7).each do |row|
      positions = []
      (0..7).each do |column|
        positions << draw(row, column)
      end
      board[row] = positions.join(' ')
    end
    board.join("\n")
  end

  def attack?
    true if same_row? || same_column? || diagonal?
  end

    private

  def draw(row, column)
    case [row, column]
    when @white
      'W'
    when @black
      'B'
    else
      '_'
    end
  end

  def same_row?
    true if @white[0] == @black[0]
  end

  def same_column?
    true if @white[1] == @black[1]
  end

  def diagonal?
    true if (@white[0] - @black[0]).abs == (@white[1] - @black[1]).abs
  end
end
