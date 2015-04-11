require 'date'
class Gigasecond
	def self.from(date)
	Date.parse((date + (10**9/(60*60*24).to_f)).to_s)
	end
end
