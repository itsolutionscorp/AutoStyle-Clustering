require 'date'
class Gigasecond
	def self.from(x)
		return Time.at(x.to_time.to_i + 10**9).to_date
	end
end
