class Gigasecond
  def self.from(birthdate)
    (birthdate.to_time + 10**9).to_date
  end
end
