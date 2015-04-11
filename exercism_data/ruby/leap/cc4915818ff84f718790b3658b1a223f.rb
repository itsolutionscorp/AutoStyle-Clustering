class Year

  def self.leap?(year)
    if Year.divisible_by_400?(year)
      return true
    elsif Year.divisible_by_100?(year)
      return false
    elsif Year.divisible_by_4?(year)
      return true
    end
    return false
  end

  def self.divisible_by_4?(year)
    year % 4 == 0
  end

  def self.divisible_by_100?(year)
    year % 100 == 0
  end

  def self.divisible_by_400?(year)
    year % 400 == 0
  end
end
