class Grains

  def square(number)
    2 ** (number-1)
  end

  def total
    64.downto(1).inject(0) { |sum, n| sum + square(n) }
  end

end
