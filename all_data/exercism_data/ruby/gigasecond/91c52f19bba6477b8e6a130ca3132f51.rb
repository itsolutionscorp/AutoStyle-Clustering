class Gigasecond

  BILLION = 10**9

  attr_reader :date

  def initialize(start_date)
    @start_time = start_date.to_time
    @date = (@start_time + BILLION).to_date
  end

end
