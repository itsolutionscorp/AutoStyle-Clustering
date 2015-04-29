class Year
  def self.leap?(year)
    return false unless potential_leap_year?(year)

    return true unless century?(year)
    return true if year % 400 == 0
    return false
  end

  private

  def self.potential_leap_year?(year)
    return true if year % 4 == 0
  end
  def self.century?(year)
    return true if year % 100 == 0
  end
end
