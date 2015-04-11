require 'date'
require 'time'

class Gigasecond

    def self.from(date_obj)
        (date_obj.to_time + (10 ** 9)).to_date
    end
end
