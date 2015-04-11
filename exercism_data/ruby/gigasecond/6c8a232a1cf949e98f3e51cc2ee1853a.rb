require 'date'
require 'time'

class Gigasecond
  gs_in_seconds = 10**9
  # gs_in_seconds = 1000000000

  def self.from(start)
    (start.to_time + gs_in_seconds).to_date
  end
end
