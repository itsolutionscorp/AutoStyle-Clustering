#!/usr/bin/env ruby

require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    giga_bday_time = date.to_time + (10**9)
    giga_bday_date = giga_bday_time.to_date
    
    return giga_bday_date
  end

end
