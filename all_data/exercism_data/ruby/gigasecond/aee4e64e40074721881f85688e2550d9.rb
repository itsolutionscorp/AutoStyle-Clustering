class Gigasecond
  GIGASECOND_IN_SECONDS = 10**9

  def self.from(date)
    (date.to_time + GIGASECOND_IN_SECONDS).to_date
  end

end
