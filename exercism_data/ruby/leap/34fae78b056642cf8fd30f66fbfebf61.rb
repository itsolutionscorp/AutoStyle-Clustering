class Year
  def self.leap?(year)
    if self.divisible_by?(year, 400)
      true
    elsif self.divisible_by?(year, 4) && !self.divisible_by?(year,100)
      true
    else
      false
    end
  end

  def self.divisible_by?(year, number)
    year % number ==  0
  end
end
