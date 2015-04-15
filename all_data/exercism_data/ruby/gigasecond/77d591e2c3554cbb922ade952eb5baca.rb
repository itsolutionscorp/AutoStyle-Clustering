#!/usr/local/bin/ruby
require 'date'
require 'time'
class Gigasecond

	def self.from(tempo)
		adc = (10**9)
		adc /= ( 60*60*24) if ( tempo.instance_of? Date)
		(tempo+adc).to_date
	end

end
