require 'Date'

class Gigasecond

    def self.from(date)
        gs = Gigasary.new date
        gs.celebration?
    end

end

class Gigasary
    attr_reader :date
    GIGASECOND = 1_000_000_000

    def initialize date
        @date = date
    end

    def celebration?
        my_gigasary_is(lots_of_seconds_after_my_birth(date))
    end

    private

    def lots_of_seconds_after_my_birth date
        date.to_time + GIGASECOND
    end

    def my_gigasary_is long_after_my_birthdate
        Time.at(long_after_my_birthdate).to_date
    end

end

