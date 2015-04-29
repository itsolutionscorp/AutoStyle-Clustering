class Year
  def self.leap?(year)
    LeapYear.new(year).leap_year?
  end
end

class LeapYear
  def initialize(year)
    @year = year
  end
  
  def leap_year?
    divisible_by(4) && !divisible_by(100) || divisible_by(400)
  end
  
  private
            
  def divisible_by(num)
    @year % num == 0
  end
end
