class Grains

  def square(sq)
    2 ** (sq - 1)
  end

  def total
#   65.times.inject do |sum, sq| # 65 (not 64) times since we should go from square 1 - 64; not 0 - 63
#     sum + square(sq)
#   end
    square(65) - 1
  end

end
