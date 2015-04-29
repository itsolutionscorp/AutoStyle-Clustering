class Gigasecond
  GIGASECOND = 10**9

  def self.from start_date
    (start_date.to_time + GIGASECOND).to_date
  end

end
