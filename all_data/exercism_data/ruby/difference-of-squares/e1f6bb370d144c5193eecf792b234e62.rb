class Squares

  def initialize(n)
    @no_of_natural_numbers = n
  end
  def sum_of_squares
   (1..@no_of_natural_numbers).inject do |sum, n|
     sum+(n**2)
   end
  end

  def square_of_sums
    sum = (1..@no_of_natural_numbers).inject(&:+)
    sum**2
  end

  def difference
   square_of_sums - sum_of_squares
  end
end
