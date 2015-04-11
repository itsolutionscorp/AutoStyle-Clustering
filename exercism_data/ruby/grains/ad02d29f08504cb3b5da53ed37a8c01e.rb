class Grains

  def square(square_number)
    2 ** (square_number - 1)
  end

  def total
    (1..TOTAL_SQUARES_COUNT).inject(0) do |result, square_number|
      result + square(square_number)
    end
  end

  TOTAL_SQUARES_COUNT = 8 * 8
end
