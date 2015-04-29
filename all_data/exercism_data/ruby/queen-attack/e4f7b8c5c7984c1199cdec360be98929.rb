class Queens
  def initialize(positions = {})
    @white = positions.fetch(:white, [0, 3])
    @black = positions.fetch(:black, [7, 3])

    fail ArgumentError, 'Pieces cannot be placed in the same square' \
      if @white == @black
  end

  attr_reader :white, :black

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  def to_s
    (0...8).map { |r| print_row(r) }.join "\n"
  end

  private

  def same_row?
    @white[0] == @black[0]
  end

  def same_column?
    @white[1] == @black[1]
  end

  def same_diagonal?
    on_diagonal_slanted_forward? || on_diagonal_slanted_backward?
  end

  def on_diagonal_slanted_forward?
    @white[0] - @white[1] == @black[0] - @black[1]
  end

  def on_diagonal_slanted_backward?
    @white[1] - @white[0] == @black[0] - @black[1]
  end

  def print_row(row)
    (0...8).map do |col|
      case
      when @white == [row, col] then 'W'
      when @black == [row, col] then 'B'
      else '_'
      end
    end.join ' '
  end
end
