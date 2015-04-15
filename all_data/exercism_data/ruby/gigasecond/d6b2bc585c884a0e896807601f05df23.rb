require 'date'
require 'time'

module Gigasecond
    @days_to_add=1000000000/(60*60*24)
    def Gigasecond.from(dt)
        dt+@days_to_add
    end
end
