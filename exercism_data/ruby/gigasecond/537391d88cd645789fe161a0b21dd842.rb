class Gigasecond
  GIGASECOND = 1_000_000_000

  def self.from birth_date
    (birth_date.to_time + GIGASECOND).to_date
  end
end
