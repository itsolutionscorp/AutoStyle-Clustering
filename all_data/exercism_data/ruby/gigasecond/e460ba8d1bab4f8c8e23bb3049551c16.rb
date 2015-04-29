class Gigasecond
  def initialize(start)
    @start = start.to_time
  end

  def date
    (@start + 1000000000).to_date
  end
end
