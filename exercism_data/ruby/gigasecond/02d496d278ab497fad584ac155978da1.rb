class Gigasecond
  attr_accessor :date
  def initialize birthdate
    @date = gigasecond_anniversary(birthdate)
  end

  private

  def gigasecond_anniversary(birthdate)
    seconds_old = birthdate.to_time.to_i
    anniversary = Time.at(seconds_old + 1000000000)
    anniversary.to_date
  end
end
