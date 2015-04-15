class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    square.call(sequence.inject(:+))
  end

  def sum_of_squares
    sequence.map(&square).inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sequence
    1.upto(@number)
  end

  def square
    ->(number) { number ** 2 }
  end
end
