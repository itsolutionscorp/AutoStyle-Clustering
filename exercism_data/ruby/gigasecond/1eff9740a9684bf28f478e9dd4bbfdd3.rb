require 'date'

module Gigasecond

	ONE_GIGASECOND = 10**9

	def self.from date
		Time.at(next_gigasecond_anniversary(date)).to_date
	end

private

	def self.next_gigasecond_anniversary(date)
		date.to_time.to_i + ONE_GIGASECOND
	end

end
