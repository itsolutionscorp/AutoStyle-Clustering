class Grains
  def square(i)
    2 ** (i - 1)
  end

  def total
    1.upto(64).inject(0) { |total, i| total + square(i) }
  end
end
