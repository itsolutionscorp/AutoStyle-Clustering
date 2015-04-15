class Queens
  attr_reader :white, :black

  def initialize(options = {})
    @white = options[:white] || [0, 3]
    @black = options[:black] || [7, 3]
    validate!

    @board = (0..7).map { |n| (0..7).map { |i| "_" } }
    @board[@white.first][@white.last] = "W"
    @board[@black.first][@black.last] = "B"
  end

  def to_s
    @board.map { |row| row.join(" ") }.join("\n")
  end

  def attack?
    same_row? || same_column? || diagonal?
  end

  private

  def validate!
    raise ArgumentError if @white == @black
  end

  def same_row?
    @white.first == @black.first
  end

  def same_column?
    @white.last == @black.last
  end

  def diagonal?
    (@white.first - @black.first).abs == (@white.last - @black.last).abs
  end
end
