SECONDS_IN_A_DAY = 60 * 60 * 24

class Integer
  def seconds
    Rational(self, SECONDS_IN_A_DAY)
  end
end

class DateTime
  def beginning_of_day
    self - self.day_fraction
  end
end

class Gigasecond
  TIME_TO_ANNIVERSARY = 1_000_000_000.seconds

  def self.from birth_date
    birth_date_time = birth_date.to_datetime
    anniversary_time = birth_date_time + TIME_TO_ANNIVERSARY
    anniversary_time.beginning_of_day
  end
end
