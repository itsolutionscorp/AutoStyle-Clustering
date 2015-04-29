class Gigasecond

  GIGASECOND = 1000000000

  def self.from date
    Time.at(date.to_time.to_i + GIGASECOND).to_date
  end
end
