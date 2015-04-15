class Year
  def initialize(year)
    @year = year
  end

  def leap?
    possible_leap_year && possible_leap_century || leap_century
  end

  def possible_leap_year
    @year % 4 == 0
  end

  def possible_leap_century
    possible_leap_year && @year % 100 != 0
  end 

  def leap_century
    possible_leap_century || @year % 400 == 0
  end
end
