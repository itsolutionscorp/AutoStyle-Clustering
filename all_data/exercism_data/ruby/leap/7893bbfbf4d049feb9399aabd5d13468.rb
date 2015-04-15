#!/usr/bin/env ruby


class Year
    def self.leap?(year)
        if year % 4 == 0 and year % 100 != 0 or year % 400 == 0
            true
        else
            false
        end
    end
end
