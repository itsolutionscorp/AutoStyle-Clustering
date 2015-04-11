class Meetup
	def initialize(month, year)
		@month, @year = month, year
	end
	@@wday = {
		:sunday    => 0,
		:monday    => 1,
		:tuesday   => 2,
		:wednesday => 3,
		:thursday  => 4,
		:friday    => 5,
		:saturday  => 6,
	}
	@@wbase = {
		:first  =>  1,
		:second =>  8,
		:third  => 15,
		:fourth => 22,
		:teenth => 13,
		:last   => -7,
	}
	def day(weekday, week_schedule)
		first = Date.new(@year, @month, @@wbase[week_schedule])
		first + (@@wday[weekday] - first.wday) % 7
	end
end
