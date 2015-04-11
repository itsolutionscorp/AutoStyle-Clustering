#!/usr/local/bin/ruby
require 'date'
require 'time'
class Gigasecond

    def self.from(d)
		data = d.to_time + (10**9)
		data.to_date
    end

end
