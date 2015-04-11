class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    divisible?(4) && (!divisible?(100) || divisible?(400))
  end

  private

  def divisible?(num)
    @year % num == 0
  end
end
