require 'date'
require 'time'

class Gigasecond
  def self.from(from_date)
    (from_date.to_time + 1E9).to_date
  end

end
