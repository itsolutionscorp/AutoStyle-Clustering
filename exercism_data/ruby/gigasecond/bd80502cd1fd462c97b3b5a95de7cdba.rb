class Gigasecond

  GIGASECOND = 10e8

  def self.from(date)
    (date.to_time + GIGASECOND).to_date
  end

end
