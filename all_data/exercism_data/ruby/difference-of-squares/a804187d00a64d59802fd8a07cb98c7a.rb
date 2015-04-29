class Squares
  attr_reader :limit

  def initialize(limit)
    @limit = limit
  end

  def square_of_sums
    sum(numbers)**2
  end

  def sum_of_squares
    sum(squares)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sum(members)
    members.inject(&:+)
  end

  def squares
    numbers.map {|n| n**2}
  end

  def numbers
    1.upto(limit)
  end
end
