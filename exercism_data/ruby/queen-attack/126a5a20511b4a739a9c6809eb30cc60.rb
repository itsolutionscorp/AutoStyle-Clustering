class Queens
  attr_reader :white, :black

  def initialize(positions = {})
    @white = positions[:white] || [0, 3]
    @black = positions[:black] || [7, 3]
    raise ArgumentError, "two queens can't be at same space" if @white == @black

    board_init
  end

  def to_s
    @board.map { |row| row.join(' ') }.join("\n")
  end

  def attack?
    return true if @white.any? { |e| @black.include? e }
    return true if (@white[0] - @black[0]).abs == (@white[1] - @black[1]).abs
  end

  private

  def board_init
    @board ||= Array.new(8) { Array.new(8, 'O') }
    @board[@white[0]][@white[1]] = 'W'
    @board[@black[0]][@black[1]] = 'B'
  end
end
