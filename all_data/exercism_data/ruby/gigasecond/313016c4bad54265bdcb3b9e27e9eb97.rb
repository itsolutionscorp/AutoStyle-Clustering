class Gigasecond

  attr_reader :date

  def initialize(start)
    (start.to_time + (10**9)).tap do |time|
      @date = Date.new(time.year, time.month, time.day)
    end
  end

end
