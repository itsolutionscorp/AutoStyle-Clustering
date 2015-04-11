require 'date'

class Gigasecond

	def self.from (object)
		date = object.to_time
		gigaseconds = 10**9
  		
		return (date + gigaseconds).to_date
	end
end
