class SquareTooBigError < StandardError; end

class Grains
  def square(square_number)
    raise SquareTooBigError if square_number > total_squares
    2**(square_number-1)
  end

  def total
    2**(total_squares)-1
  end

  private

  def total_squares
    64
  end
end
