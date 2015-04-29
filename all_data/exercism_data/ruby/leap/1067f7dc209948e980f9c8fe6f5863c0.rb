class Year
  def self.leap?(year)
    divisible_by_four?(year) and
      not divisible_by_hundred?(year) or
      divisible_by_four_hundred?(year)
  end

  def self.divisible_by_four?(year)
    year % 4 == 0
  end

  def self.divisible_by_hundred?(year)
    year % 100 == 0
  end

  def self.divisible_by_four_hundred?(year)
    year % 400 == 0
  end
end
