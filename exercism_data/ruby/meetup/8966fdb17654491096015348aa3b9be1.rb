class Meetup
	def initialize(month, year)
		@month = month
		@year = year
	end

	ORDERS = %w(first second third fourth)

	Date::DAYNAMES.each do |day| 
		define_method("#{day.downcase.gsub('day', '')}teenth") do
			teenth(day.downcase)
		end
	end


	private

	def teenth(day)
		Date.new(@year, @month, 13).step(Date.new(@year, @month, 19)).find(&:"#{day}?")
	end


	def monthly
		Date.new(@year, @month, 13).step(Date.new(@year, @month, 19)).select {|d| d.monday?}
	end

end
