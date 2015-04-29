# Leap years are lots of fun
class Year
  def self.leap?(year)
    return true if divisble_by_400(year)
    return false if divisible_by_100(year)
    return true if divisible_by_4(year)
  end

  private

  def self.divisble_by_400(year)
    (year % 400) == 0
  end

  def self.divisible_by_100(year)
    (year % 100) == 0
  end

  def self.divisible_by_4(year)
    (year % 4) == 0
  end
end
