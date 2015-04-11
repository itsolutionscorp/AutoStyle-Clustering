class Gigasecond
  def self.from(date)
    date_timestamp = date.to_time.to_i
    gigasecond_anniversary_timestamp = date_timestamp + (10 ** 9)

    Time.at(gigasecond_anniversary_timestamp).to_date
  end
end
