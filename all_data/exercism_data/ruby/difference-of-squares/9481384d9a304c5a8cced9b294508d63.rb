##John Youngblood
##1/27/2015
class Squares
  def initialize(arg)
    @n = arg
  end

  def square_of_sums
    num = 0
    1.upto(@n) do |x|
      num += x
    end
    num**2
  end

  def sum_of_squares
    num = 0
    1.upto(@n) do |x|
      num += x**2
    end
    num
  end

  def difference
    square_of_sums-sum_of_squares
  end
end
