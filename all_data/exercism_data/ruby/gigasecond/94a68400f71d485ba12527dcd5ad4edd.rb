require 'time'
require 'date'

# Add Gigasecond module
class Gigasecond
	# Add from method
	def self.from(input)
		# Input to time (input) because it's easier to work with
		# Add gigaseconds
		# Then return new date (date) as a date type
		input = input.to_time
		date = input + (10**9)
		date = date.to_date
		return date
	end
end
