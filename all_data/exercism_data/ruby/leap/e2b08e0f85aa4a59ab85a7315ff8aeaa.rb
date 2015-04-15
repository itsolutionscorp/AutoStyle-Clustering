class Year
  def initialize year
    @year = year
  end

  def self.leap? year
    year = Year.new(year)

    if year.divisible_by? 100
      year.divisible_by? 400
    else
      year.divisible_by? 4
    end
  end

  def divisible_by? divisor
    @year % divisor == 0
  end
end
