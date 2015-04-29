class Gigasecond

  SECONDS_IN_A_DAY = 86400

  def self.from(date)
    date + Rational(10**9,SECONDS_IN_A_DAY).to_i
  end
end
