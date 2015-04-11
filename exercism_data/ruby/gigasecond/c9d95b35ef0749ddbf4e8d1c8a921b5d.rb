class Gigasecond
  attr_accessor :start_date

def initialize(start_date)
    self.start_date = start_date
  end

  def date
    start_date + 11574
  end
  
end
