require 'date'
require 'time'
class Gigasecond
  
  def self.from(birthday_date)
    birthday_date + (10**9)
  end
  
end
