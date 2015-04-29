require 'pry'
require 'date'
require 'time'

# jill lynch exercism problem
class Gigasecond

  def self.from(date_1)
     #binding.pry
	 	 (date_1.to_time + 10**9).to_date
  end


end
