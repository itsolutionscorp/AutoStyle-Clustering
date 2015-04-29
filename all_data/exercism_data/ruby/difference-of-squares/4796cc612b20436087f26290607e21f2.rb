class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    square_root.call(sequence.inject(:+))
  end

  def sum_of_squares
    sequence.map(&square_root).inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sequence
    1.upto(@number)
  end

  def square_root
    ->(number) { number ** 2 }
  end
end
