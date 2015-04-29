class Gigasecond
  attr_accessor :date
  def initialize date
    self.date = add_a_billion_seconds(date)
  end

  def add_a_billion_seconds date
    (date.to_time + (10**9)).to_date
  end
end
