class Year
  def initialize(year)
    @year = year
  end
  
  def divisible_by? number
    @year % number == 0
  end
  
  def leap?
    self.divisible_by? 4 and not self.divisible_by? 100 or
        self.divisible_by? 400
  end
  
  def self.leap?(year)
    Year.new(year).leap?
  end
end
