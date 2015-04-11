class Gigasecond
  def self.from(date)
    date_to_unix = date.to_time.to_i # UNIX Timestamp
    gigasecond = 10**9 # A gigasecond (1000000000)

    Time.at(date_to_unix + gigasecond).to_date
  end
end
