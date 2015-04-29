require 'date'

class Meetup

	DATES = { monday: 1, tuesday: 2, wednesday: 3, thursday: 4, friday: 5, saturday: 6, sunday: 7 }
	POSITION = { first: 0, second: 1, third: 2, fourth: 3, last: -1 }
	attr_reader :month, :year

	def initialize(month, year)
		@month = month
		@year = year
	end

	def day(dow, position)
		position == :teenth ? final_dow = select_teenth(dow) : final_dow = select_day(dow,position)	
		Date.new(year, month, final_dow)
	end

	private

		def dates
			(Date.new(year, month, 1).day..Date.new(year, month, -1).day).to_a
		end

		def to_date_hash
			dates.each_with_object({}) { |x, hash| hash[x] = Date.new(year, month, x).cwday }
		end

		def select_day(day, position)
			to_date_hash.select{ |k, v| v == DATES[day]}.to_a[POSITION[position]][0]
		end

		def select_teenth(day)
			to_date_hash.select{ |k, v| k >= 13 && k <= 19 && v==DATES[day]}.flatten[0]
		end

end
