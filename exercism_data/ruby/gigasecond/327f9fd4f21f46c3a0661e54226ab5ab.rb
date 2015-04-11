class Gigasecond

  ONE_GIGASECOND = 1000000000
  def self.from(date)
    Time.at(date.to_time.to_i + ONE_GIGASECOND).gmtime
  end
end
