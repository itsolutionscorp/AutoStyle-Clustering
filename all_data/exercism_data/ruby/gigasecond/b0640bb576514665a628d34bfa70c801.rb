require 'date'
require 'time'

class Gigasecond
  attr_reader :date

  def initialize(birthday_date)
  	gigasecond_delta = (1000000000 / (3600 * 24))
    @date = birthday_date + gigasecond_delta
  end
  
end
