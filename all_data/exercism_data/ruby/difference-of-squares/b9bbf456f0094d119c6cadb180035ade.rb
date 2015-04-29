class Squares
  attr_accessor :natural_numbers
  private :natural_numbers

  def initialize(top_number)
    self.natural_numbers = (1 .. top_number)
  end

  def square_of_sums
    natural_numbers.reduce(&:+) ** 2
  end

  def sum_of_squares
    natural_numbers.map { |number| number ** 2 }.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
