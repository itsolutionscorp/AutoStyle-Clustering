class Queens
  attr_reader :white, :black

  def initialize(args = {})
    @white = args[:white] || [0,3]
    @black = args[:black] || [7,3]
    raise ArgumentError if white == black 
  end

  def attack?
    horizontal? || vertical? || diagnal?
  end

  def to_s
    set(white, 'W')
    set(black, 'B')
    grid.map { |row| row.join(' ') }.join("\n")
  end

  private

  def grid
    @grid ||= Array.new(8) { Array.new(8) { '_' } }
  end

  def set(pos, sym)
    x,y = pos
    grid[x][y] = sym
  end

  def horizontal?
    white[0] == black[0]
  end

  def vertical?
    white[1] == black[1]
  end

  def diagnal?
    w = tile_value(white)
    b = tile_value(black)
    # difference between diagnals are divisble by 7 and 9 for 8X8 grid
    (w-b) % 9 == 0 || 
    (w-b) % 7 == 0
  end

  def tile_value(queen)
    # tile value derived from 8X8 chess grid numbered 1-64
    # (row * 8) + (col + 1)
    queen[0] * 8 + queen[1] + 1
  end
end
