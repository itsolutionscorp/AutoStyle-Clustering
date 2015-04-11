class Gigasecond
  attr_accessor :date
  def initialize date
    self.date = (date.to_time + (10**9)).to_date
  end
end
