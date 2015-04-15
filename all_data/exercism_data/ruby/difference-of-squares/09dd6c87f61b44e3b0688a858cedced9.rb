class Squares
  attr_reader :upper_limit

  def initialize(upper_limit)
    @upper_limit = upper_limit
  end

  def square_of_sums
    (1..upper_limit).reduce(:+) ** 2
  end

  def sum_of_squares
    (1..upper_limit).map {|el| el * el}
                    .reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
