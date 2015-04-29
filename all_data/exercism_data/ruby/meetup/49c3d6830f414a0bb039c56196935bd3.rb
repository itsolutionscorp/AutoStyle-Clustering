require 'date'

class Meetup
	def initialize(month, year)
		@month = month
		@year = year
	end

	def day(weekday, schedule)
		current = Date.new(@year, @month, 1)
		current += 1 while current.strftime("%A").downcase != weekday.to_s
		
		counter = 1
		last = current
		while current.month == @month
			return current if current.day > 12 && schedule.to_s == 'teenth'

			return current if counter == 1 && schedule.to_s == 'first'
			return current if counter == 2 && schedule.to_s == 'second'
			return current if counter == 3 && schedule.to_s == 'third'
			return current if counter == 4 && schedule.to_s == 'fourth'

			last = current
			current += 7
			counter += 1
		end

		return last
	end
end
