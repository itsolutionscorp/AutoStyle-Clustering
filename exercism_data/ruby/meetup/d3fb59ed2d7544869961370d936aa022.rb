class Meetup
	def initialize(month, year)
		@month = month
		@year = year
	end

	def teenth(day)

	end

	def monteenth
		Date.new(@year, @month, 13).step(Date.new(@year, @month, 19)).select {|d| d.monday?}
	end
end
