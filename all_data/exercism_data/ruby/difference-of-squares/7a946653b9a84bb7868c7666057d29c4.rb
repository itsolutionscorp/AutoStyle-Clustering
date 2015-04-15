class Squares
  def initialize(last_number)
    @last_number = last_number 
  end

  def square_of_sums
    1.upto(@last_number).reduce(:+) ** 2
  end

  def sum_of_squares
    1.upto(@last_number).reduce { |sum, n| sum += n ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
