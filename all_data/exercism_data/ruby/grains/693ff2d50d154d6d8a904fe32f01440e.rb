class Grains
  attr_reader :multiplier, :total_squares

  def initialize(multiplier = 2, total_squares = 64)
    @multiplier = multiplier
    @total_squares = total_squares
  end

  def square(number)
    multiplier ** (number - 1)
  end

  def total
    (1..total_squares).inject(0) { |grains, number| grains += square(number) }
  end
end
