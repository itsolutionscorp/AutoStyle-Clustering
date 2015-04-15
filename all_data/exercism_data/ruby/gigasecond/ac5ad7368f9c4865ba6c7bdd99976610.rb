require 'date'
require 'time'

class Gigasecond
  def self.from(input_date)
    days_in_a_gigasecond = 10**9/86400
    futuredate = input_date + days_in_a_gigasecond
  end
end
