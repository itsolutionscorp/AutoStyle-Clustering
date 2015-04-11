# A gigasecond is one billion (10**9) seconds.

class Gigasecond
	def self.from(fromDate)
		Time.at(fromDate.to_time.to_i + 10**9).to_date
	end
end
