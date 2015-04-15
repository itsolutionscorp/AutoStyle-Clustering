class Gigasecond
  def initialize(start_date)
    self.start_date = start_date
  end

  def date
    start_date + 11574 #number of days in a gigasecond
  end

  private
  attr_accessor :start_date
end
