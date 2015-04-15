class Squares
  def initialize(to_number)
    @to_number = to_number
  end

  def square_of_sums
    (0..@to_number).inject(0, :+)**2
  end

  def sum_of_squares
    (0..@to_number).map do |number|
      number**2
    end.inject(0, :+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
