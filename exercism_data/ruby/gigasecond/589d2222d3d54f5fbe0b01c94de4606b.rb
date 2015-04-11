class Gigasecond

  def self.from(initial_date)
    @@initial_date = initial_date
    seconds_to_date_from(date_as_seconds_plus_a_billion)
  end

  def self.seconds_to_date_from(seconds)
    Time.at(seconds).to_date
  end

  def self.date_as_seconds_plus_a_billion
    @@initial_date.to_time.to_i + (10**9)
  end

end
