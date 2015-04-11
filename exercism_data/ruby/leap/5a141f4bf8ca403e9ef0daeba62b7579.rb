class Year

  def self.leap?(year)
    true if divisible_by?(year, 4) && 
            !divisible_by?(year, 100) ||
             divisible_by?(year, 400)
  end

  def self.divisible_by?(int, unit)
    true if int % unit == 0
  end
end
