#!/usr/bin/env ruby
require 'date'
require 'time'
GIGASECOND = 1_000_000_000
ONE_DAY = 1*60*60*24

class Gigasecond
  def self.from(birth_date)
    @giga_anniversary = birth_date
    @giga_days = GIGASECOND / ONE_DAY

    @giga_anniversary = @giga_anniversary + @giga_days
  end
end
