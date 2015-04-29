require 'Date'

class Gigasecond

  def self.from(x)
    start_date = x
    gigaseconds = 10**9
   ( start_date.to_time + gigaseconds ).to_date
  end

end



puts Gigasecond.from(Date.new(1991, 01, 26))
