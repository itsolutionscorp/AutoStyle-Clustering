class Gigasecond
  ONE_GIGASECOND_SECS = 10**9

  def self.from(birthdate)
    (birthdate.to_time + ONE_GIGASECOND_SECS).to_date
  end
end
