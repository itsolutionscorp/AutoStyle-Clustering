SECONDS_IN_A_DAY = 60 * 60 * 24
TIME_TO_ANNIVERSARY = Rational(1_000_000_000, SECONDS_IN_A_DAY)

class Gigasecond
  def self.from birth_date
    birth_date_time = birth_date.to_datetime
    anniversary_time = birth_date_time + TIME_TO_ANNIVERSARY
    beginning_of_day(anniversary_time)
  end
  
  private
  
  def self.beginning_of_day(datetime)
    datetime - datetime.day_fraction
  end
end
