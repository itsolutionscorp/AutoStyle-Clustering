require 'date'
require 'time'
class Gigasecond
	def self.from(date_from)
		$giga_date=Time.parse(date_from.to_s) + (10**9)
		Date.new($giga_date.year,$giga_date.mon,$giga_date.mday)
	end
end
