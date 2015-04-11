class Gigasecond

  def self.from(birthdate)
    t = birthdate.to_time
    giga = t + 10**9
    giga.to_date
  end

end
