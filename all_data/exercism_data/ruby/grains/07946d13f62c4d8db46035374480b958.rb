class Grains

  TOTAL_SQUARES = 64

  def initialize
    @squares = put_grains
  end

  def square(square_number)
    @squares.fetch(square_number - 1)
  end

  def total
    @squares.reduce(:+)
  end

  private

  def put_grains
    all_squares = (0..(TOTAL_SQUARES - 1)).to_a
    grains_on = all_squares.map { |square| 2**square}

    return grains_on
  end

end
