class Grains
  attr_reader  :values

  def initialize(number_of_squares = 64)
    @values ||= (0..number_of_squares - 1).map { |x| 2 ** x }
  end

  def square(number)
    grains = @values[number - 1]
  end

  def total
    @values.inject(&:+)
  end
end
