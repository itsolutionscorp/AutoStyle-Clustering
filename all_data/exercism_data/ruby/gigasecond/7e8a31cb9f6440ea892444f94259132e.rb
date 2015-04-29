class Gigasecond
  def initialize(date)
    @date = date
  end
  def date
    new_date = Date.parse((@date + Rational(10 ** 9, 60 * 60 * 24)).to_s)
  end
end
