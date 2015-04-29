class Gigasecond
  GIGASECOND = 1_000_000_000

  def initialize(start_date)
    @start_time = start_date.to_time
  end

  def date
    (start_time + GIGASECOND).to_date
  end

private
  attr_reader :start_time
end
