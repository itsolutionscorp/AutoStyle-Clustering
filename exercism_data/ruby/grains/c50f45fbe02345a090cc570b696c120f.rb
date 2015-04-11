class Grains
  SQUARES_COUNT = 64

  def initialize
    @squares = [1]
  end

  def square(num)
    computed_squares(num).last
  end

  def total
    computed_squares(SQUARES_COUNT).reduce(:+)
  end

  private
  def computed_squares(num)
    need_compute = num - @squares.count
    need_compute.times { compute_next_square }
    @squares[0, num]
  end

  def compute_next_square
    @squares << @squares.last * 2
  end
end
