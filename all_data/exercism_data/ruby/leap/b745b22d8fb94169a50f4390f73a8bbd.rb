class Year
  def self.leap?(year)
    Year.new(year).fourth_year_without_exceptions?
  end

  def initialize(year)
    @year = year
  end

  def fourth_year_without_exceptions?
    @year%4 == 0 && !invalid_century?
  end

  def invalid_century?
    @year%100 == 0 && !fourth_century?
  end

  def fourth_century?
    @year%400 == 0
  end
end
