class Gigasecond
  GIGASECOND = 10 ** 9

  def self.from moment
    (moment.to_time + GIGASECOND).to_date
  end
end
