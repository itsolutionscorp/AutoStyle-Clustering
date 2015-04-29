class Squares
  def initialize n
    @n = n
  end

  def difference
    square_of_sums-sum_of_squares
  end

  def square_of_sums
    sum**2
  end

  def sum_of_squares
    1.upto(@n).inject(0) {|sum, n| sum + n**2 }
  end

  private

  def sum
    (@n*(@n+1))/2
  end
end
