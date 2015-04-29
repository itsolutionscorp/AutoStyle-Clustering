class Year
  def initialize(year)
    @year = year
  end

  def divisible_by?(divisor)
    @year % divisor == 0
  end

  def self.leap?(year)
    year = Year.new(year)
    year.divisible_by?(400) || year.divisible_by?(4) && !year.divisible_by?(100)
  end
end
