class Squares
  attr_reader :natural_number_limit

  def initialize(natural_number_limit)
    @natural_number_limit = natural_number_limit
  end

  def natural_numbers
    (1..natural_number_limit)
  end

  def square_of_sums
    natural_numbers.reduce(:+)**2
  end

  def sum_of_squares
    natural_numbers.inject { |sum, nn| sum + nn**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
