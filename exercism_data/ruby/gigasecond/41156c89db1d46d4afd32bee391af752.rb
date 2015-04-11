class Gigasecond

  GIGA_SECOND = 10 ** 9

  def self.from(from)
    (Time.at(from.to_time.to_i) + GIGA_SECOND).to_date
  end

end
