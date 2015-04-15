require 'date'
require 'time'
# class Gigasecond
class Gigasecond
  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    @birth_date + (1_000_000_000 / 60 / 60 / 24)
  end
end
