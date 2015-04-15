class Squares
  def initialize(number)
    @sequence = Sequence.new(1..number)
  end

  def square_of_sums
    @sequence.sum ** 2
  end

  def sum_of_squares
    @sequence.squares.sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end

class Sequence
  include Enumerable

  def initialize(sequence)
    @sequence = sequence
  end

  def sum
    @sequence.inject(:+)
  end

  def squares
    Sequence.new(@sequence.map { |n| n ** 2 })
  end
end
