class Grains
  def square(nth)
    raise IndexError unless 0 < nth and nth <= 64 and nth % 1 == 0
    2**(nth-1)
  end

  def total
    (0..64).to_a.inject{|sum, n| sum + square(n)}
  end
end
