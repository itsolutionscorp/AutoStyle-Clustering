class Year
  def self.leap?(year)
    divisible_by?(year, 4) && century_leap?(year)
  end

private
  def self.century_leap?(year)
    if divisible_by?(year, 400)
      return true
    else
      return !(divisible_by?(year, 100))
    end
  end

  def self.divisible_by?(year, number)
    year%number==0 ? true : false
  end
end
