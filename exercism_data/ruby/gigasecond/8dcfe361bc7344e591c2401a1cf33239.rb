class Gigasecond

  def initialize(from_date)
    a_billion_seconds_in_days = 11_574
    @date = from_date + a_billion_seconds_in_days
  end

  attr_reader :date

end
