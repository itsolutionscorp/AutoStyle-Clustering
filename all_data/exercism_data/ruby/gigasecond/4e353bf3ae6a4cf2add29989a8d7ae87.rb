class Gigasecond

  @gigasecond = 10**9

  def self.from date
    Time.at(date.to_time.to_i + @gigasecond).to_date
  end
end
