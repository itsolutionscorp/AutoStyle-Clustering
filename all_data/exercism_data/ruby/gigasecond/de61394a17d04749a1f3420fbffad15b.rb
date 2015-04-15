class Gigasecond

  def self.from(date)
    date_in_seconds = in_seconds(date)
    date_plus_one_gigasecond_in_seconds = date_in_seconds + one_gigasecond
    Time.at(date_plus_one_gigasecond_in_seconds).to_date
  end

private

  def self.in_seconds(date)
    date.to_time.to_i
  end

  def self.one_gigasecond
    1_000_000_000
  end

end
