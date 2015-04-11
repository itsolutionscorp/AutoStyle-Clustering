class Queens
  attr_reader :white, :black

  def initialize(options = {})
    @white = options.fetch(:white, [0, 3])
    @black = options.fetch(:black, [7, 3])

    if @white == @black
      fail ArgumentError,
           "Cannot set white and black on the same square (#{@white})"
    end
  end

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  def to_s
    row  = '_ _ _ _ _ _ _ _'
    board = "#{row}\n#{row}\n#{row}\n#{row}\n#{row}\n#{row}\n#{row}\n#{row}"

    board[to_index @white] = 'W'
    board[to_index @black] = 'B'

    board
  end

  private

  def to_index((y, x))
    x * 2 + y * 8 * 2
  end

  def same_row?
    @white[1] == @black[1]
  end

  def same_column?
    @white[0] == @black[0]
  end

  def same_diagonal?
    bw = @white[1] - @white[0]
    bb = @black[1] - @black[0]

    bw == bb || bw == -bb
  end
end
