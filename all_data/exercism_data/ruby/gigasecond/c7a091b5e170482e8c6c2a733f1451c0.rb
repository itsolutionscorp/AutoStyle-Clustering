# Module for gigasecond calculations.
module Gigasecond
    # Returns the date that's a gigasecond after the given date.
    def self.from(birth_date)
        birth_time = birth_date.to_time.to_i
        Time.at(birth_time + 10**9).to_date
    end
end
