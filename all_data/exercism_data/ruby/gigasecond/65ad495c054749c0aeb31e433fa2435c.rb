class Gigasecond

  GIGA_SECOND = 10**9

  def self.from(date)
    (date.to_time + GIGA_SECOND).to_date
  end

end
