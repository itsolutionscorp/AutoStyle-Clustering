class Gigasecond
  def self.from(date)
    date + seconds_to_days
  end

  private
  def self.seconds_to_days
    ((((10**9)/ 60) / 60) / 24)  # 11574 days...shut up
  end
end
