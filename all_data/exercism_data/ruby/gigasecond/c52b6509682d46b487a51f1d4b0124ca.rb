require 'date'
require 'time'

class Gigasecond

  @@gigasec = 10**9

  def self.from(date)
    (date.to_time + @@gigasec).to_date
  end

end
