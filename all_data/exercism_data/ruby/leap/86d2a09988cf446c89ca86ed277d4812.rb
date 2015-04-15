class Year
  def self.leap?(year)
    return true if Year.century_leap?(year)
    unless Year.century?(year)
      return true if Year.divisible_by_four?(year)
    end
    false
  end

  def self.century_leap?(year)
    year % 400 == 0
  end

  def self.century?(year)
    year % 100 == 0
  end

  def self.divisible_by_four?(year)
    year % 4 == 0
  end
end
