class Squares
  attr_reader :n
  def initialize(n)
    @n = n
  end

  def square_of_sums
    n.downto(1).inject(&:+)**2
  end

  def sum_of_squares
    n.downto(1).map{|x| x**2}.inject(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
