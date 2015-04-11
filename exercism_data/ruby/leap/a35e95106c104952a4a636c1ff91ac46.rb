class Year
  attr_reader :year
  def initialize year
    @year = year
  end

  def leap?
    dividable_by_4 = (year % 4).zero?
    dividable_by_100 = (year % 100).zero?
    dividable_by_400 = (year % 400).zero?
    dividable_by_4 && !dividable_by_100 || dividable_by_400
  end
end
