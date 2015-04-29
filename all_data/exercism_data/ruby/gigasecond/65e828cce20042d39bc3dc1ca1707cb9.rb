class Gigasecond
  GIGASECONDS = 10**9

  def self.from(birth_date)
    self.calculate_gigasec_anniversary(birth_date)
  end

  def self.calculate_gigasec_anniversary(birth_date)
    (birth_date.to_time + GIGASECONDS).to_date
  end

end
