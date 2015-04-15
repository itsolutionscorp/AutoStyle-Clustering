class Gigasecond

  SECONDS_IN_A_DAY = 86400
  GIGASECOND       = Rational(10**9,SECONDS_IN_A_DAY).to_i

  def self.from(date)
    date + GIGASECOND
  end
end
