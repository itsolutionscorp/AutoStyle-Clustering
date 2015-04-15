require 'date'
require 'time'

class Gigasecond
  def self.from(gigaday)
    @gigas = 10**9
    if gigaday.is_a? Date 
      while (@gigas / (24*60*60)) >= 1
        @gigas -= 24*60*60
        gigaday = gigaday.next_day
      end
    elsif gigaday.is_a? Time
      temp = gigaday+@gigas
      gigaday = Date.new(temp.year,temp.mon,temp.day) 
    else
      puts "the given value is not of type 'Date' nor 'Time'"
    end
    gigaday
  end
end
