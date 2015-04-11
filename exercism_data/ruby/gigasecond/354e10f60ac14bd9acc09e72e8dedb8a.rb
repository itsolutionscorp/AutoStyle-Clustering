require 'date'
require 'time'
module Gigasecond
   extend(self)

   def from(date)
     date_plus_gigasecond = date.to_time + 1000000000
     date_plus_gigasecond.to_date
   end
end
