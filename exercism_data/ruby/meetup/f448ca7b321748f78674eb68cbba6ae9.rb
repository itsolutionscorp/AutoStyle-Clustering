class Meetup

	attr_reader :month, :year
	
	def initialize(month, year)
		@month = month
		@year = year
	end
	
	WEEK = [ :monday, :tuesday, :wednesday, :thursday, :friday, :saturday, :sunday ]
	
	def day(weekday, schedule)
		number = WEEK.index(weekday) + 1	
		if schedule == :teenth
			return teenth(weekday, number)
		elsif schedule == :last
			return last(weekday, number)
		else
			return st_nd_rd_th(weekday, schedule, number)
		end
	end
	
	def teenth(weekday, number)
		(13..19).each do |teenth|
			return day = Date.new(year, month, teenth) if Date.new(year, month, teenth).cwday == number
		end
	end
	
	def st_nd_rd_th(weekday, schedule, number)
		range = if schedule == :first
							1..7
						elsif schedule == :second
							8..14
						elsif schedule == :third
							15..21
						elsif schedule == :fourth
							22..28
						elsif schedule == :fifth
							29..31
						end
						
		range.each do |day|	
			return Date.new(year, month, day) if Date.new(year, month, day).cwday == number
		end
	end
	
	def last(weekday, number)
		-1.downto(-7) do |last|
			return Date.new(year, month, last) if Date.new(year, month, last).cwday == number
		end		
	end

end
