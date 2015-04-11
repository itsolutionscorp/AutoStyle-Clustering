class Meetup
	def initialize(month, year)
		@month = month
		@year	= year
		@teenthday = Date.new(@year, @month, 13)
		@month_first_day = @teenthday - 12
		@week_day = @teenthday.wday
	end

	WEEK_DAY = {'SUNDAY'    => 0,
	        'MONDAY'    => 1,
	        'TUESDAY'   => 2,
	        'WEDNESDAY' => 3,
	        'THURSDAY'  => 4,
	        'FRIDAY'    => 5,
	        'SATURDAY'  => 6}

  def monteenth
    @teenthday.monday? ? @teenthday : teenth('monday')
	end

	def tuesteenth
		@teenthday.tuesday? ? @teenthday : teenth('tuesday')
	end

	def wednesteenth
		@teenthday.wednesday? ? @teenthday : teenth('wednesday')
	end

	def thursteenth
    @teenthday.thursday? ? @teenthday : teenth('thursday')
	end

	def friteenth
    @teenthday.friday? ? @teenthday : teenth('friday')
	end

	def saturteenth
		@teenthday.saturday? ? @teenthday : teenth('saturday')
	end

	def sunteenth
		@teenthday.sunday? ? @teenthday : teenth('sunday')
	end

	def first_sunday
		sundays = extract_from_month 'sunday'
		sundays[0]
	end

	def first_monday
		mondays = extract_from_month 'monday'
		mondays[0]
	end

	def first_tuesday
		tuesdays = extract_from_month 'tuesday'
		tuesdays[0]
	end

	def first_wednesday
		wednesdays = extract_from_month 'wednesday'
		wednesdays[0]
	end

	def first_thursday
		thursdays = extract_from_month 'thursday'
		thursdays[0]
	end

	def first_friday
		fridays = extract_from_month 'friday'
		fridays[0]
	end

	def first_saturday
		saturdays = extract_from_month 'saturday'
		saturdays[0]
	end

	def second_sunday
		sundays = extract_from_month 'sunday'
		sundays[1]
	end

	def second_monday
		mondays = extract_from_month 'monday'
		mondays[1]
	end

	def second_tuesday
		tuesdays = extract_from_month 'tuesday'
		tuesdays[1]
	end

	def second_wednesday
		wednesdays = extract_from_month 'wednesday'
		wednesdays[1]
	end	

	def second_thursday
		thursdays = extract_from_month 'thursday'
		thursdays[1]
	end

	def second_friday
		fridays = extract_from_month 'friday'
		fridays[1]
	end

	def second_saturday
		saturdays = extract_from_month 'saturday'
		saturdays[1]
	end

	def third_sunday
		sundays = extract_from_month 'sunday'
		sundays[2]
	end

	def third_monday
		mondays = extract_from_month 'monday'
		mondays[2]
	end

	def third_tuesday
		tuesdays = extract_from_month 'tuesday'
		tuesdays[2]
	end

	def third_wednesday
		wednesdays = extract_from_month 'wednesday'
		wednesdays[2]
	end

	def third_thursday
		thursdays = extract_from_month 'thursday'
		thursdays[2]
	end	

	def third_friday
		fridays = extract_from_month 'friday'
		fridays[2]
	end

	def third_saturday
		saturdays = extract_from_month 'saturday'
		saturdays[2]
	end

	def fourth_sunday
		sundays = extract_from_month 'sunday'
		sundays[3]
	end

	def fourth_monday
		mondays = extract_from_month 'monday'
		mondays[3]
	end

	def fourth_tuesday
		tuesdays = extract_from_month 'tuesday'
		tuesdays[3]
	end

	def fourth_wednesday
		wednesdays = extract_from_month 'wednesday'
		wednesdays[3]
	end

	def fourth_thursday
		thursdays = extract_from_month 'thursday'
		thursdays[3]
	end	

	def fourth_friday
		fridays = extract_from_month 'friday'
		fridays[3]
	end

	def fourth_saturday
		saturdays = extract_from_month 'saturday'
		saturdays[3]
	end

	def last_sunday
		sundays = extract_from_month 'sunday'
		sundays.last
	end

	def last_monday
		mondays = extract_from_month 'monday'
		mondays.last
	end

	def last_tuesday
		tuesdays = extract_from_month 'tuesday'
		tuesdays.last
	end

	def last_wednesday
		wednesdays = extract_from_month 'wednesday'
		wednesdays.last
	end

	def last_thursday
		thursdays = extract_from_month 'thursday'
		thursdays.last
	end	

	def last_friday
		fridays = extract_from_month 'friday'
		fridays.last
	end

	def last_saturday
		saturdays = extract_from_month 'saturday'
		saturdays.last
	end

	def extract_from_month(day)
		days = []
		while @month_first_day.mon == @month
			if @month_first_day.wday == WEEK_DAY[day.upcase]
				days << @month_first_day
			end
			@month_first_day += 1
		end
		return days
	end

	def teenth(day)
		if @week_day < WEEK_DAY[day.upcase]
		  @teenthday + (WEEK_DAY[day.upcase] - @week_day)
		else
			@teenthday + (WEEK_DAY.size - (@week_day - WEEK_DAY[day.upcase]).abs)
		end
	end

end
