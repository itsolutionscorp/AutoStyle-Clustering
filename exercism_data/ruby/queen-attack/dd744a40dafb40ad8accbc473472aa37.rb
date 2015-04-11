class Queens
  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3])
    fail ArgumentError, "cannot both occupy #{white}" if white == black
    @white = white
    @black = black
  end

  def to_s
    rows.map { |row| row.join(' ') }.join("\n")
  end

  def rows
    Array.new(8) { Array.new(8, '_') }.tap do |rows|
      rows[white[0]][white[1]] = 'W'
      rows[black[0]][black[1]] = 'B'
    end
  end

  def attack?
    white[0] == black[0] || white[1] == black[1] ||
      (white[0] - black[0]).abs == (white[1] - black[1]).abs
  end
end
