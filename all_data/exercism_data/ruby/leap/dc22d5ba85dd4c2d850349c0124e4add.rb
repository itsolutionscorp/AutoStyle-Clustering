class Year
  def self.leap?(year)
    Integer(year).leap?
  end
end

class Integer
  def divisible_by?(n)
    self % n == 0
  end

  def leap?
    divisible_by?(4) and (!divisible_by?(100) || divisible_by?(400))
  end
end
