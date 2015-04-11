class Grains
  def square(power)
    2 ** (power - 1)
  end

  def total
    0.upto(63).inject(0) {|sum, x| sum + 2 ** x}
  end
end
