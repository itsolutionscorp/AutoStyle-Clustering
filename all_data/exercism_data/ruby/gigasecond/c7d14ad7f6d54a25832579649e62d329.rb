class Gigasecond
  attr_accessor :start_date

  def initialize(start_date)
    self.start_date = start_date
  end

  def date
    start_date + (11574) # ((10 ** 9)/(24 * 60 * 60))
  end

end
