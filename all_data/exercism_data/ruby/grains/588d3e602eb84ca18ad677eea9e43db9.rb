class Grains
  attr_reader :total_number_of_squares

  def initialize(total_number_of_squares=64)
    @total_number_of_squares = total_number_of_squares
  end

  def square(number)
    grain_calculation_cache[number] ||= calculate_number_of_grains(number)
  end

  def total
    (1..total_number_of_squares).reduce(0) do |sum, square_number|
      sum + square(square_number)
    end
  end

  private

  def calculate_number_of_grains(number)
    if number == 1
      1
    else
      2 * calculate_number_of_grains(number - 1)
    end
  end

  def grain_calculation_cache
    @grain_calculation_cache ||= Hash.new(nil)
  end
end
