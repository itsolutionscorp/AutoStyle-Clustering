require 'time'
require 'date'

class Gigasecond 
    def self.from (date)
        date + (10**9).divmod(60)[0].divmod(60)[0].divmod(24)[0]
    end
end
