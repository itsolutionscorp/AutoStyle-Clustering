class Squares

  attr_reader :number

  def initialize(nb)
    @number = nb
  end

  def square_of_sums
    @square ||= (1..number).inject(:+) ** 2
  end

  def sum_of_squares
    @sum ||= (1..number).map { |i| i**2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
