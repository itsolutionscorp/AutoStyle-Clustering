require 'date'
require 'time'

class Gigasecond

def self.new (input)
	output =(input.to_time + (10**9)).to_date
	return output
end

end
