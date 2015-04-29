class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    LeapYearCalculator.new(year).qualifies?
  end
end

class LeapYearCalculator
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def qualifies?
    divisable_by_4? && not_divisable_by_100_unless_multiple_of_400?
  end

  def not_divisable_by_100_unless_multiple_of_400?
    return true if divisable_by_400?
    !divisable_by_100?
  end

  [4, 100, 400].each do |year_count|
    define_method("divisable_by_#{year_count}?") do
      year % year_count == 0
    end
  end
end
