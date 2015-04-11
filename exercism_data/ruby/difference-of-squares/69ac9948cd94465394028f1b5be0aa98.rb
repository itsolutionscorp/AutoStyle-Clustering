class Squares

  def initialize( limit )
    @numbers = 1..limit
    square_of_sums
    return self
  end

  def square_of_sums #[sic]
    @square_of_sum ||=
    square( sum_of_numbers )
  end

  def sum_of_squares
    @sum_of_squares ||=
    sum( squares_of_numbers.each {|n| square(n)} )
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sum( numbers )
    sum = 0
    numbers.each do |number|
      sum += number
    end
    sum
  end

  def square( number )
    number ** 2
  end

  def sum_of_numbers
    sum( @numbers )
  end

  def squares_of_numbers
    @numbers.map {|n| square(n)}
  end

end
