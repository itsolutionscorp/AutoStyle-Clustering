class Grains
  def square(square)
    2 ** (square - 1)
  end

  def total
    (1..63).reduce(1) { |total, i| total += (2 ** i) }
  end
end
