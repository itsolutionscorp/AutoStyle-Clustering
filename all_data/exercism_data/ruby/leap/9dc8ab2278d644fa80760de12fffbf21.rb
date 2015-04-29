# A class for checking if a year is a leap year.
class Year

  # Checks if a given year is a leap year.
  def self.leap?(year)
    if divisible_by?(400, year)
      true
    elsif divisible_by?(100, year)
      false
    elsif divisible_by?(4, year)
      true
    else
      false
    end
  end

  # Checks if a given year is divisible by a given number.
  def self.divisible_by?(number, year)
    year % number == 0
  end
end
