class Year
  def self.leap?(year)
    self.new(year).is_leap_year?
  end

  def initialize(year)
    @year = year
  end

  def is_leap_year?
    fourth_year? && not_century_or_fourth_century?
  end

  private

  attr_reader :year

  def fourth_year?
    year % 4 == 0
  end

  def not_century?
    year % 100 != 0
  end

  def fourth_century?
    year % 400 == 0
  end

  def not_century_or_fourth_century?
    not_century? || fourth_century?
  end
end
