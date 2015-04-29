class Year
  def self.divisible_by?(year,number)
    year % number == 0
  end
  def self.leap?(year)
    return true if (divisible_by?(year, 400)) ||
                   (divisible_by?(year, 4) && !divisible_by?(year,100))
    return false
  end
end
