class Gigasecond

  SECONDS_FORWARD = 10**9

  attr_reader :date

  def initialize(start_date)
    @date = (start_date.to_time + SECONDS_FORWARD).to_date
  end

end
