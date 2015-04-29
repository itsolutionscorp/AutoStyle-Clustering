class Gigasecond
  GIGASECOND = 10**9
  def self.from(birthdate_or_time)
    (birthdate_or_time.to_time + GIGASECOND ).to_date
  end
end
