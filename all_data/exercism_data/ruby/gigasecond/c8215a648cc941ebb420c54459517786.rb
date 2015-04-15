require 'date'

class Gigasecond
  GIGA_SECOND = 1000 ** 3

  def self.from(from_time)
    case from_time
    when Date,Time then
      to_time = from_time.to_datetime + Rational(GIGA_SECOND , 24 * 60 * 60)
    when DateTime  then
      to_time = from_time + Rational(GIGA_SECOND , 24 * 60 * 60)
    else
      raise StandardError
    end

    to_time.to_date
  end
end
