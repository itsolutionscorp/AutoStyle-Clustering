require 'date'
require 'time'

class Gigasecond
  
  GIGA_SEC = 1_000_000_000.0
  DAY_SEC = 86_400.0
  
  def self.from(arg1)
    giga_bday = self.get_dt(arg1.to_time) + self.seconds_to_days
    Date.new(giga_bday.year, giga_bday.month, giga_bday.day)
  end
  
  private
  
  def self.seconds_to_days(sec: GIGA_SEC)
    sec / DAY_SEC
  end
  
  def self.get_dt value
    DateTime.new value.year, value.month, value.day, value.hour, value.min, value.sec
  end
  
end
