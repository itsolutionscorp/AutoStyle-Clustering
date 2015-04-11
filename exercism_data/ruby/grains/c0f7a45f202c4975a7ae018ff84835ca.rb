class Grains

  def square(square)
    2 ** (square - 1)
  end

  def total
    (1..64).reduce {|sum, grains| sum + square(grains)}
  end

end
