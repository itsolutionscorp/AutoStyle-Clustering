class Year
  def self.leap?(year)
    @year = year

    fourth_century? || leap_year? && century?
  end

private

  def self.leap_year?
    @year % 4 == 0
  end

  def self.century?
    @year % 100 != 0
  end

  def self.fourth_century?
    @year % 400 == 0
  end
end
