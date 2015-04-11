class Gigasecond
  def initialize(birthdate)
    @birthdate  = birthdate
  end

  def date
    @birthdate + 1000000000/ 86400
  end
end
