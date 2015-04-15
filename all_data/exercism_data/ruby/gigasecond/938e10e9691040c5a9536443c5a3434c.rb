# Create Gigasecond module
class Gigasecond
	# Create from method
	def self.from(date)
		# Return anniversary date
		return (date.to_time + 10**9).to_date
	end
end
