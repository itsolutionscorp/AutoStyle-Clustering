class Squares
  def initialize(n)
    @n = n
  end

  def difference
   square_of_sums - sum_of_squares
  end

  def square_of_sums
    (1..@n).inject(0) {|sum , item| sum + item}**2
  end

  def sum_of_squares
    (1..@n).inject(0) {|sum , item| sum + item * item}
  end

end
