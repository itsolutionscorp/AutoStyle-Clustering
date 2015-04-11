require 'date'

class Meetup
	WEEKDAYS = [ :sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday ]
								
	SCHEDULES = [	:first, :second, :third, :fourth, :last, :teenth ]
								
	TEENTH = 13

	def initialize(month, year)
		@calendar = every_day_of(month, year)
	end
	
	def day(weekday, schedule)
		case schedule
		when :last
			all(weekday).last
		when :teenth
			all(weekday, TEENTH).first
		else
			all(weekday)[SCHEDULES.index(schedule)]
		end
	end
	
private
	def every_day_of(month, year)
		first_day = Date.new(year, month, 1)
		last_day = first_day.next_month.prev_day
		(first_day..last_day).to_a
	end
	
	def all(weekday, from = 1)
		@calendar.select { |date| (date.day >= from) && (date.wday == WEEKDAYS.index(weekday)) }
	end
end
