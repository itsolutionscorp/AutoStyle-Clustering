require 'date'

class Gigasecond
  class << self
    def from(date)
      gigasecond = epoch_time(date) + (10**9) 
      Date.strptime(gigasecond.to_s, '%s')
    end

    private

    def epoch_time(date)
      date.to_time.to_i
    end
  end
end
