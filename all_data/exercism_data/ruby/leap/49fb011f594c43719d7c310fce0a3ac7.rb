class Year
  def initialize(year)
    @year = year
  end
  
  def divisible_by? number
    @year % number == 0
  end
  
  def leap?
    divisible_by? 4 and not divisible_by? 100 or divisible_by? 400
  end
  
  def self.leap?(year)
    Year.new(year).leap?
  end
end
