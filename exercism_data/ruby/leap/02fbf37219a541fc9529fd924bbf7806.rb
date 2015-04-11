module Year

  def self.leap?(year)
    regular_leap_year(year) &&
    (!regular_century(year) || four_hundred_century(year))
  end

  private

  def self.regular_leap_year(year)
    year % 4 == 0
  end

  def self.regular_century(year)
    year % 100 == 0
  end

  def self.four_hundred_century(year)
    year % 400 == 0
  end
end
