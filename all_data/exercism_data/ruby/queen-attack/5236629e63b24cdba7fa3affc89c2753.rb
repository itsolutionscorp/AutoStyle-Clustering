class Queens
  attr_reader :white, :black

  def initialize(options = {})
    @white = options.fetch(:white, [0, 3])
    @black = options.fetch(:black, [7, 3])
    raise ArgumentError if white == black
  end

  def to_s
    board_array.map {|ary| ary.join(" ") }.join("\n")
  end

  def attack?
    ATTACK_VECTORS.include?(slope)
  end

  private

  ATTACK_VECTORS = [ 1, 0, Float::INFINITY ]

  def slope
    (rise.to_f / run).abs
  end

  def rise
    wx - bx
  end

  def run
    wy - by
  end

  def string_row
    Array.new(8, 'O')
  end

  def board_array
    Array.new(8) { string_row }.tap do |ary|
      ary[wx][wy] = "W"
      ary[bx][by] = "B"
    end
  end

  def wx
    white[0]
  end

  def wy
    white[1]
  end

  def bx
    black[0]
  end

  def by
    black[1]
  end
end
