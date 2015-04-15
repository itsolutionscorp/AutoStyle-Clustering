module Gigasecond

  def self.from(date)
    if date.class == Date
      time = date.to_time
      time += 1_000_000_000
      time.to_date
    elsif date.class == Time
      date += 1_000_000_000
      date.to_date
    end
  end

end
