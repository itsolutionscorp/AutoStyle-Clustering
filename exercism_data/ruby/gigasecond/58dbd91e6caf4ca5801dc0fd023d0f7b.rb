require 'date'

class Gigasecond

  def self.from(date)
    gigaseconds = 10**9
    seconds_per_day = 24*60*60
    date + (gigasecond / seconds_per_day)
  end

end
