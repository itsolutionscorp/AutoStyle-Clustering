class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    even_into_four = (year % 4).zero?
    even_into_one_hundred = (year % 100).zero?
    even_into_four_hundred = (year % 400).zero?

    even_into_four &&
      ((!even_into_one_hundred) || (even_into_one_hundred && even_into_four_hundred))
  end
end
