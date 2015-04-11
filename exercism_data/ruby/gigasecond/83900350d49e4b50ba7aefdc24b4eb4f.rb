class Gigasecond
  def initialize(start_date)
    @start_time = start_date.to_time
  end

  def date
    (@start_time + 10**9).to_date
  end
end
