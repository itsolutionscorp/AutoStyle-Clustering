class Gigasecond
  GIGASECOND = (10 ** 9)

  def self.from(date)
    add_seconds(date).to_date
  end

  def self.add_seconds(date)
    date.to_time + GIGASECOND
  end
end
