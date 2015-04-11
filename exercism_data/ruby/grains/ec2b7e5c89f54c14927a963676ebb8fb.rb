class Grains
  def square(x)
    2**(x - 1)
  end

  def total
    (1..64).inject(0) {|sum, v| sum + square(v)}
  end
end
