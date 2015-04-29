module Gigasecond

	require 'date'

	def self.from date
		Time.at(next_megasecond_anniversary(date)).to_date
	end

private

	def self.next_megasecond_anniversary(date)
		date.to_time.to_i + one_megasecond
	end

	def self.one_megasecond
		10**9
	end

end
