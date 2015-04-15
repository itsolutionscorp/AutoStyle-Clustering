require 'date'
require 'time'
class Gigasecond
 def self.from(input)
  if input.class == Date
   return input + 1000000000/60/60/24
  end
  if input.class == Time
   return Date.parse((input + 1000000000).to_s)
  end 
 end
end
