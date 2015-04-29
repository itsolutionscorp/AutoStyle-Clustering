class Squares
  def initialize(number)
    @range = 1..number
  end

  def sum_of_squares
    sqs = @range.collect do |number|
      number**2
    end
    sqs.reduce(:+)
  end

  def square_of_sums
    @range.reduce(:+)**2
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
