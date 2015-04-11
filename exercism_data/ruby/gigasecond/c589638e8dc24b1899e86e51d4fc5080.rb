require 'date'
require 'time'

class Gigasecond

  GIGASECONDS = 10**9

  # calculates date one gigasecond from given date
  def self.from date
    (date.to_time + GIGASECONDS).to_date
  end

end
