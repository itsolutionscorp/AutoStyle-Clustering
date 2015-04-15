class Squares
  attr_accessor :int

  def initialize(int)
    @int = int
  end

  def square_of_sums
    sums ** 2
  end

  def sum_of_squares
    squares.reduce(:+)
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

  private

  def sums
    (0..int).reduce(:+)
  end

  def squares
    (0..int).map { |int| int ** 2 }
  end

end
