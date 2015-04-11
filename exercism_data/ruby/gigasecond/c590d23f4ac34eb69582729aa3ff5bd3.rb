require 'date'
require 'time'

class Gigasecond
  def self.from date
    date = date.to_time if date.class.eql? Date
    Time.at(date.to_i + 10**9).to_date
  end
end
