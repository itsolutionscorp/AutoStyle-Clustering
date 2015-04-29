class Gigasecond
  def self.from(date)
    return (date + 1000000000.seconds - (1000000000 % 1000000000.days).seconds)
  end
end

class Fixnum
  def seconds
    Rational(self, 86400)
  end

  def days
    self / 60 / 60 / 24
  end
end
