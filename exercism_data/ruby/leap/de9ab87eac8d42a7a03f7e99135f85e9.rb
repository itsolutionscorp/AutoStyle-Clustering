class Year

  attr_reader :year

  def initialize number
    @year = number.to_i
  end

  def leap?
    (!century? && divisible_by_4?) || leap_century?
  end

  def leap_century?
    @year % 400 == 0
  end

  def century?
    @year % 100 == 0
  end

  def divisible_by_4?
    @year % 4 == 0
  end

end
