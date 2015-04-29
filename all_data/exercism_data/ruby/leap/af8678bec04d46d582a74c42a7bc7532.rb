class Year
  def self.leap?(year)
    Year.new(year).leap_year?
  end

  def initialize(year)
    @year = year
  end

  def leap_year?
    @year%4 == 0 && !regular_century?
  end

  def regular_century?
    @year%100 == 0 && !leap_century?
  end

  def leap_century?
    @year%400 == 0
  end
end
