class Year
  def self.leap?(year)
    return true if year.divisible_by_4? && !year.divisible_by_100?
    return true if year.divisible_by_4? && year.divisible_by_100? && year.divisible_by_400?
    return false
  end
end

class Integer
  def divisible_by_4?
    self % 4 == 0
  end
  def divisible_by_100?
    self % 100 == 0
  end
  def divisible_by_400?
    self % 400 == 0
  end
end
