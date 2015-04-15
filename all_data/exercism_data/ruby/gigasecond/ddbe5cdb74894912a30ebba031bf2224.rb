# I need to do `require 'date'` in test file.
class Gigasecond
  GIGASECOND_IN_DAYS = 10 ** 9 / 86400

  def initialize(birthdate)
    @birthdate = birthdate
  end

  def date
    @birthdate + GIGASECOND_IN_DAYS
  end
end
