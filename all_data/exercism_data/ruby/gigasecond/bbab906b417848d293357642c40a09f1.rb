require 'time'
require 'date'

class Gigasecond

  def self.from(date)
    if date.is_a?(Date)
      date + 10**9/86400
    else
      date = date + 10**9
      date.to_date
    end
  end
end
