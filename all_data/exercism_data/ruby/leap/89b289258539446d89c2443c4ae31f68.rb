class Year
  def initialize(year)
    @year = year
  end

  def self.leap?(year)
    Year.new(year).leap?
  end

  def leap?
    is_leap_century? || (is_simple_leap? && !is_century?)
  end

private
  def is_simple_leap?
        (@year % 4) == 0
  end

  def is_century?
    (@year % 100) == 0
  end

  def is_leap_century?
    (@year % 400) == 0
  end


end
