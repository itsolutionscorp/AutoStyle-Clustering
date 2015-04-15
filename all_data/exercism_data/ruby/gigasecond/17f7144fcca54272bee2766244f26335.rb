require 'date'
require 'time'

class Gigasecond
	def self.from(born_at)
		if born_at.class == Date
			time_born_at = born_at.to_time
		elsif born_at.class == Time
			time_born_at = born_at
		else
			puts "Bad input, please enter Date or Time object"
		end
		anniversary = (time_born_at + 1000000000).to_date
	end
end
