class Gigasecond

  GIGA_SECOND = 1000000000 

  def self.from(date)
       (date.to_time + GIGA_SECOND ).to_date
  end
end
