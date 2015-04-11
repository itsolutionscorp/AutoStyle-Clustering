require 'date'
require 'time'
# class Gigasecond
class Gigasecond
  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    (@birth_date.to_time + 1_000_000_000).to_date
  end
end
