class Year
  def self.leap?(input)
    LeapYear.new(input).valid?
  end
end

class LeapYear
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def valid?
    four_hundred? || (four? && !hundred?)
  end

  def four?
    year % 4 == 0
  end

  def hundred?
    year % 100 == 0
  end

  def four_hundred?
    year % 400 == 0
  end
end
