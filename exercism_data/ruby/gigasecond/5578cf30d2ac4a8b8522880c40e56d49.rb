class Gigasecond
  @gs = 10**9

  def self.from date
    (date.to_time + @gs).to_date
  end
end
