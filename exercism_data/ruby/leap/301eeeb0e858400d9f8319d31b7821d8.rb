class Year
  def self.leap?(year)
    divisible(year, 4) && (!divisible(year, 100) || divisible(year, 400))
  end

  private

  def self.divisible(year, num)
    year % num == 0
  end
end
