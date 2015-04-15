class Gigasecond

  def self.from(date)
    seconds_to_date_from(date_as_seconds_plus_a_billion_from(date))
  end

  def self.seconds_to_date_from(seconds)
    Time.at(seconds).to_date
  end

  def self.date_as_seconds_plus_a_billion_from(date)
    date.to_time.to_i + (10**9)
  end

end
