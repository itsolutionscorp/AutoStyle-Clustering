=begin
  File: gigasecond.rb
  Author: sherinom
=end

class Gigasecond

	def initialize(date)
		@date = date
	end

	def date
		@date + 10**9 / (60 * 60 * 24)
	end

end
