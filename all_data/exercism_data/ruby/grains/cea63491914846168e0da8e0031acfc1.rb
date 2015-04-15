class Grains
  def square(i)
    2 ** (i - 1)
  end

  def total
    1.upto(64).map(&method(:square)).reduce(:+)
  end
end
