class Gigasecond

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    @birthday + Rational(10**9/86400)
  end

end
