#gigasecond.rb

class Gigasecond
	def self.from(date)
		if date.is_a?(Time)
			date += 1000000000
			Date.parse(date.to_s)
		else
			date + (1000000000/60/60/24)
		end
	end
end
