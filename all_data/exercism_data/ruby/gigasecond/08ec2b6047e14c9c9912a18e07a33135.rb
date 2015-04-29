require 'date'
require 'time'

class Gigasecond
  def initialize(date)
    @birthdate = date
  end

  def date
    seconds = @birthdate.to_time.to_i + 10**9
    Date.strptime(seconds.to_s, '%s')
  end
end
