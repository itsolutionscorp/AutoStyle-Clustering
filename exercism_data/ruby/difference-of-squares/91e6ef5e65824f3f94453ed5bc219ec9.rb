class Squares
  attr_accessor :integer

  def initialize(integer)
    @integer = integer
  end

  def sum_of_squares
    total = 0
    integer = @integer
    until integer < 0
      total += integer**2
      integer -= 1
    end
    total
  end

  def square_of_sums
    total = 0
    integer = @integer
    until integer < 0
      total += integer
      integer -= 1
    end
    total**2
  end

  def int
    Squares.new(integer)
  end

  def difference
    int.square_of_sums - int.sum_of_squares
  end
end
