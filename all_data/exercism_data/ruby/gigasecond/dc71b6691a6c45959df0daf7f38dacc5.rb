require 'date'
class Gigasecond

  def self.from(born_date)
    calculator = GigasecondCalculator.new(born_date)
    calculator.calculate
  end
end

class GigasecondCalculator
  def initialize(born_date)
    @born_date = born_date
  end
  
  def calculate
    @born_date + gigasecond_to_day
  end

  def gigasecond_to_day
    (10**9)/(60*60*24)
  end
end    
