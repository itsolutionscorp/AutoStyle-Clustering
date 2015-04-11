class Gigasecond
  GIGA_SECOND = 10**9

  def self.from(birth_date)
    (birth_date.to_time + GIGA_SECOND).to_date
  end
end
