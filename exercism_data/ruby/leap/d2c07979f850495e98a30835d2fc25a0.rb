class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    leap_candidate? && valid_century? || exceptional_century?
  end

  def leap_candidate?
    year % 4 == 0
  end

  def valid_century?
    year % 100 != 0
  end

  def exceptional_century?
    year % 400 == 0
  end
end
