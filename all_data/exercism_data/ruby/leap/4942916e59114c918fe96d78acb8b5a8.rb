class Year
  def self.leap?(year)
    @@year = year
    return true if (divisible_by?(4) && !divisible_by?(100)) || divisible_by?(400)
    false
  end

  def self.year
    @@year
  end

  private

  def self.divisible_by?(number)
    year % number == 0
  end
end
