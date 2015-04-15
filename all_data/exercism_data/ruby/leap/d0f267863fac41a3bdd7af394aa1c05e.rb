class Year

  def self.leap?(year)
    return false if fails_century_rule?(year)
    return true  if divisible_by?(year, 4)
  end

  private

  def self.fails_century_rule?(year)
    divisible_by?(year, 100) && !divisible_by?(year, 400)
  end

  def self.divisible_by?(number, divisor)
    number % divisor == 0
  end

end
