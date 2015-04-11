class Year
  def self.leap?(year)
    return false unless divisible_by?(year, 4)

    is_century_but_not_fourth?(year) ? false : true
  end

  private
  def self.divisible_by?(number, numerator)
    number % numerator == 0
  end

  def self.is_century_but_not_fourth?(year)
    divisible_by?(year, 100) && !divisible_by?(year, 400)
  end
end
