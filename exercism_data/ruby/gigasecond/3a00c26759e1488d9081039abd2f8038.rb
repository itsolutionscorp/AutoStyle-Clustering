class Gigasecond
  ONE_GIGASECOND = 1_000_000_000
  
  def self.from(date)
    (date.to_time + ONE_GIGASECOND).to_date
  end
end
