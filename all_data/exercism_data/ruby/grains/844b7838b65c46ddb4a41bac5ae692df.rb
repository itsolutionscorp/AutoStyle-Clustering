class Grains
  def square(i)
    @square = { 1 => 1 }
    @square[i] ||= 2 * square(i - 1)
  end

  def total
    1.upto(64).map(&method(:square)).reduce(:+)
  end
end
