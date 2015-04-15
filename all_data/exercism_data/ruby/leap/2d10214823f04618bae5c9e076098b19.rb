# A year (CE)
class Year
  # Create new year from given number
  def initialize(year)
    @year = year
  end
  
  # Return true if this year is divisible by given number
  def divisible_by?(number)
    @year % number == 0
  end
  
  # Return true if this year is a leap year
  def leap?
    divisible_by?(4) and not divisible_by?(100) or divisible_by?(400)
  end
  
  # Return true if given number denotes a leap year
  def self.leap?(year)
    new(year).leap?
  end
end
