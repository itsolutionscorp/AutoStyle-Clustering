module Gigasecond

	def self.from(date)
		if date.respond_to? :to_time
			(date.to_time + 10**9).to_date
		else
			nil # Should I raise an error here, or is nil fine?
		end
	end

end
