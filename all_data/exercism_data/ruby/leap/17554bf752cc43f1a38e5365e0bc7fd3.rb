class Year
  DIVISIBLE = [4, 100, 400]

  def initialize year
    @year = year
  end

  def self.leap? year
    new(year).leap?
  end

  def leap?
    divisible?(4) && (!divisible?(100) || divisible?(400))
  end

  private

  def divisible? mod
    @year % mod == 0
  end
end
