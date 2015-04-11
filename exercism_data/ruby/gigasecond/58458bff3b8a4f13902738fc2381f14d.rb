class Gigasecond
  class << self
    def from(start)
      new(start: start).end_time.to_date
    end
  end

  GIGASECOND = 10**9

  attr_reader :start_time, :end_time

  def initialize(start: start)
    @start_time = start.to_time
    @end_time   = start_time + GIGASECOND
  end
end
