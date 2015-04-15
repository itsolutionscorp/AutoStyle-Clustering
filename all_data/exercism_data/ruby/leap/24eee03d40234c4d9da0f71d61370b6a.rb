class Year
  def self.leap?(year)
    divisible_by?(year, 4) && !divisible_by?(year, 100) || divisible_by?(year, 400)
  end

  def self.divisible_by?(year, number)
    year % number == 0
  end
end
