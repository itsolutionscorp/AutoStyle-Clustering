class Gigasecond
  def self.from(born_date)
    born_date + Rational(10**9, 60*60*24).to_i
  end
end
