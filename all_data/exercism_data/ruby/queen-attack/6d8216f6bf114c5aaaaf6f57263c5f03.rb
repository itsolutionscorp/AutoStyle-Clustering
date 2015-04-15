class Queens
  attr_reader :white, :black

  Tile = Struct.new(:x, :y)

  def initialize(args = {})
    @white = args.fetch(:white, [0,3])
    @black = args.fetch(:black, [7,3])
    raise ArgumentError if white == black 
  end

  def attack?
    w, b   = Tile.new(*white), Tile.new(*black)
    dx, dy = (w.x-b.x).abs, (w.y-b.y).abs

    w.x == b.x ||  # same row
    w.y == b.y ||  # same col
    dx == dy       # same diagnal
  end

  def to_s
    board.map { |row| row.join(' ') }.join("\n")
  end

  private

  def board
    @board ||= Array.new(8) { Array.new(8) { '_' } }
    set(white, 'W')
    set(black, 'B')
    @board
  end

  def set(queen, sym)
    pos = Tile.new(*queen)
    @board[pos.x][pos.y] = sym
  end
end
