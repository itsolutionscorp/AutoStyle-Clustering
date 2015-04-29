require 'date'
require 'time'

class Gigasecond

    def self.from(date_obj)
        birthdate = date_obj.to_time.strftime('%s').to_i
        Time.at(birthdate + (10 ** 9)).to_date
    end
end
