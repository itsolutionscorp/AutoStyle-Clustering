require 'date'
require 'time'

class Gigasecond
  attr_reader :gs

  def self.from(date)
    (date.to_time + 10**9).to_date
  end
end
