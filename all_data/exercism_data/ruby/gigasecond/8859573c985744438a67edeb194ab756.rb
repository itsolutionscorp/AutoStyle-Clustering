class Gigasecond

  GIGASECOND = 10**9 # A gigasecond (1000000000)

  def self.from(date)
    date_to_unix = date.to_time.to_i # UNIX Timestamp
    Time.at(date_to_unix + GIGASECOND).to_date
  end
end
