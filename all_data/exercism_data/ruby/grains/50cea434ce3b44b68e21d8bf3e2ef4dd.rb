class Grains
  
  NUM_TILES = 64

  def initialize
    @square_cache = {}
  end

  def square(num)
    @square_cache[num] || (@square_cache[num] = calc_square(num))
  end

  def calc_square(num)
    num == 1 ? 1 : 2 * square(num - 1)
  end

  def total
    (1..NUM_TILES).inject(0) {|sum, num| sum + square(num)}
  end

end
