require 'time'
require 'date'
require 'pp'

class Gigasecond
  def self.from(start_date_time)
    #Forces input to time adds 1 billion seconds and converts to date before returning
    (start_date_time.to_time + 10 ** 9).to_date
  end
end
