class Year

  def initialize(year)
    @year = year
  end

  def leap?
    if divisible_by?(400)
      true
    elsif divisible_by?(100)
      false
    else
      common_definition
    end
  end

  private 

  def common_definition
    divisible_by?(4)
  end

  def divisible_by?(divisor)
    year % divisor == 0
  end

  attr_reader :year
end
