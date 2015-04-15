class Year
  def self.leap?(year)
    return true if year.divisible_by?(4) && !year.divisible_by?(100)
    return true if year.divisible_by?(4) && year.divisible_by?(100) && year.divisible_by?(400)
    return false
  end
end

class Integer
  def divisible_by?(n)
    self % n == 0
  end
end
