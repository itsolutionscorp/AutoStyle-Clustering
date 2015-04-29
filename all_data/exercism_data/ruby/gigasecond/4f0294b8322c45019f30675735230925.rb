class Gigasecond
  OFFSET = 10**9

  def self.from(startpoint)
    (startpoint.to_time + OFFSET).to_date
  end
end
