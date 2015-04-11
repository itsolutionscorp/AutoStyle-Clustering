require 'date'
require 'time'

class Gigasecond

  def self.from(birthday)
    number_of_days_in_a_gigasecond = 10**9 / (24*3600)
    birthday + number_of_days_in_a_gigasecond  
  end

end
