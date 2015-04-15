require 'date'

class Gigasecond
  def self.from(datetime)
    datetime = datetime + 10 ** 9
    Date.new(datetime.year, datetime.month, datetime.day)
  end
end

# redefine "+" method for Date class
# so that it can calculate seconds
class ::Date
  def +(sec)
    time = Time.new(year, month, day)
    time = time + sec
    Date.new(time.year, time.month, time.day)
  end
end
