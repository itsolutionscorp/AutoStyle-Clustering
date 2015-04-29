class Year

  def initialize(year)
    @year = year
  end

  def leap?
    if divisible_by_400?
      true
    elsif divisible_by_100?
      false
    else
      divisible_by_4?
    end
  end

  private 

  def divisible_by_400?
    year % 400 == 0
  end

  def divisible_by_100?
    year % 100 == 0
  end

  def divisible_by_4?
    year % 4 == 0
  end

  attr_reader :year
end
