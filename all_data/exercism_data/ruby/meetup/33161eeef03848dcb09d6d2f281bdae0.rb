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
	def day(weekday, week)
		base = case week
		when :first;   0
		when :second;  7
		when :third;  14
		when :fourth; 21
		when :teenth; 12
		when :last;   Date.new(@year, @month, -1).day-7
		end
		first = Date.new(@year, @month)
		first+base+(@@wday[weekday]-(base+first.wday))%7
	end
end
