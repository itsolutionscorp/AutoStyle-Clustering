class Gigasecond

  def initialize(start_date)
    self.start_date = start_date
  end


  def date
    start_date + 11574 # number of days in a gigasecond: 1000000000s / (24*60*60) 
  end

  private
  attr_accessor :start_date

end
