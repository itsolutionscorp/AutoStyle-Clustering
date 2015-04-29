module Gigasecond
  # return [Date]
  def self.from(d)
    from_time(d.to_time).to_date
  end

  # return [Time]
  def self.from_time(t)
    t + 1_000_000_000
  end
end
