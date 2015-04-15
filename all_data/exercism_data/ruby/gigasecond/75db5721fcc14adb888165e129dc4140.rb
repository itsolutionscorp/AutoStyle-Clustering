class Gigasecond
  attr_accessor :date

  def initialize(date)
    days = 1_000_000_000 / (60*60*24) 
    self.date = date + days 
  end

end
