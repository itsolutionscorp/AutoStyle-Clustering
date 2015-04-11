class Gigasecond

  GIGA_SECOND = 10 ** 9

  def self.from(from)
    (from.to_time + GIGA_SECOND).to_date
  end

end
