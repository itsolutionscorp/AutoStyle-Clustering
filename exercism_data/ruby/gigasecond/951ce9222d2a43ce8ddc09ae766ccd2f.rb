class Gigasecond
  def initialize(start_date)
    @start_date = start_date
  end

  def date
    (@start_date.to_time + 1_000_000_000).to_date
  end
end
