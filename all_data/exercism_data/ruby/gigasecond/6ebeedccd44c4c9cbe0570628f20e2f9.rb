require 'date'
require 'time'
class Gigasecond


	def self.from(date)
		d = Date.parse((Time.parse(date.to_s) + 10**9).to_s)
		return d
	end

end
