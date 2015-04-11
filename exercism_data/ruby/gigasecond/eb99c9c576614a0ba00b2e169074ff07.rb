=begin Pseudocode
Put in date (year, month, day). Get back date. Calculate the new date by ensuring that the difference is one billion seconds (10**9).

Convert date to a number that represents the date from a reference time. Add 1 billion seconds to that number. Reconvert the reference number back to a date.
=end


class Gigasecond

	attr_reader :date_unix

	def initialize(date_object)
		@date_unix = date_object.to_time.to_i
	end

	def date
		return Time.at(date_unix + 10**9).to_date
	end

end
