class Year
  def self.leap?(year)
    @@year=year
    divisible_by?(4) && century_leap?
  end

private
  def self.century_leap?
    if divisible_by?(400)
      return true
    else
      return !(divisible_by?(100))
    end
  end

  def self.divisible_by?(number)
    @@year%number==0 ? true : false
  end
end
