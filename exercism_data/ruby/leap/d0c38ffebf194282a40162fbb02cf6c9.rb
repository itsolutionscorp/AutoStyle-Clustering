class Year

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if    divisible_by?(400) then true
    elsif divisible_by?(100) then false
    elsif divisible_by?(4)   then true
    else  false
    end
  end

  private

  def divisible_by?(n)
    year % n == 0
  end

end
