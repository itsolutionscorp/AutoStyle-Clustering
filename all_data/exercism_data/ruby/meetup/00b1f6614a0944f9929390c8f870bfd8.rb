class Meetup
	require 'date'
	SCHEDULE = {
		first: (1..7).to_a,
		second: (8..14).to_a,
		third: (15..21).to_a,
		fourth: (22..28).to_a,
		teenth: (13..19).to_a,
		last: nil
	}
	WEEKDAY = {
		monday: Proc.new {|x| x.monday?},
		tuesday: Proc.new {|x| x.tuesday?},
		wednesday: Proc.new {|x| x.wednesday?},
		thursday: Proc.new {|x| x.thursday?},
		friday: Proc.new {|x| x.friday?},
		saturday: Proc.new {|x| x.saturday?},
		sunday: Proc.new{|x| x.sunday?}
	}
	def initialize(month, year)
		@month = month
		@year = year
	end

	def day(weekday, schedule)
		day = WEEKDAY[weekday]
		sched = SCHEDULE[schedule]
		sched = last(@month) if schedule == :last
		result = nil
		sched.select{|x| day.call(Date.new(@year,@month,x))}.each{|x| result = x}
		Date.new(@year,@month,result)
	end

	private
	def last(arg)
		case 
		when [1,3,5,7,8,10,12].include?(arg)
			(25..31).to_a
		when [4,6,9,10,11].include?(arg)
			(24..30).to_a
		when [2].include?(arg)
			(21..28).to_a
		else
			raise ArgumentError 
		end
	end
end
