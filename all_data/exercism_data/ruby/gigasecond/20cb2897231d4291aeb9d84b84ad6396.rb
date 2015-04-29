class Gigasecond

  def self.from(date)
    date_in_gigaseconds = Time.at(date.to_time.to_i + 10**9).strftime("%Y-%m-%d")
    Date.parse(date_in_gigaseconds)
  end

end
