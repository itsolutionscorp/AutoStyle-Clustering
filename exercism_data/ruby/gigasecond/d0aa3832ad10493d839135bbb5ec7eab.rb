require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    (date.to_time + 10**9).to_date
    #start = date.to_time.to_i
    #anniversary = start + 10**9
   # anniversary = Time.at(anniversary).to_datetime.to_date
  end
end
