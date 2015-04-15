class Grains
  def square(num)
    2 ** (num - 1)
  end

  def total
    1.upto(64).inject(0) { |x,i| x + square(i) }
  end
end
