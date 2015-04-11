class Year
  def self.leap?(year)
    divisible_by?(year, 4) && ((divisible_by?(year, 100) && divisible_by?(year, 400)) ||
    (!divisible_by?(year, 100)))
  end

  def self.divisible_by?(year, division)
    year%division == 0
  end
end
