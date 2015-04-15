require "date"

class Meetup

	def initialize(month, year)
		@month = month
		@year = year
	end

	@@WEEKDAYS = {
		sunday:    0,
		monday:    1,
		tuesday:   2,
		wednesday: 3,
		thursday:  4,
		friday:    5,
		saturday:  6,
	}

	@@ORDINALS = {
		first:  0,
		second: 1,
		third:  2,
		fourth: 3
	}

	def day(weekday, schedule)
		wd = @@WEEKDAYS[weekday]
		case schedule
		when :last
			last(wd)
		when :teenth
			teenth(wd)
		else
			nth(wd, @@ORDINALS[schedule])
		end
	end

	private

		def dateFrom(start, offset, weeks=0)
			Date.new(@year, @month, start + offset + weeks*7)
		end

		def days_until(weekday, day_of_month)
			day = weekday - Date.new(@year, @month, day_of_month).wday
			day >= 0 ? day : day + 7 
		end

		def teenth(weekday)
			dateFrom(13, days_until(weekday, 13))
		end

		def nth(weekday, n)
			dateFrom(1, days_until(weekday, 1), n)
		end

		def last(weekday)
			dateFrom(days_in_month - 6, days_until(weekday, -7))
		end
		
		def days_in_month
			Date.new(@year, @month, -1).day
		end
end
