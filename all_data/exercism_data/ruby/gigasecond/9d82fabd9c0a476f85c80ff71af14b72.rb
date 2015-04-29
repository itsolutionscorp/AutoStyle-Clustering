class Gigasecond

  def initialize(birthdate)
    @birthdate = birthdate
  end

  def date
    @birthdate + ((10**9) / 86400)
  end

end
